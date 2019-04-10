package jschool.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jschool.dao.CategoryDAO;
import jschool.dao.ProductDAO;
import jschool.dto.CategoryRawDTO;
import jschool.dto.ProductDTO;
import jschool.helpers.ImageHelper;
import jschool.model.Category;
import jschool.model.Product;
import jschool.service.DTOConverterService;
import jschool.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private DTOConverterService dtoConverterService;
    private final ModelMapper modelMapper;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;


    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, DTOConverterService dtoConverterService,
                              ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.modelMapper = modelMapper;
        this.dtoConverterService = dtoConverterService;
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public List<Product> list() {
        return  this.productDAO.list();

    }

    @Override
    @Transactional
    public Set<ProductDTO> listDTO(){
        return dtoConverterService.getProductDTOList(list());
    }
    @Override
    @Transactional
    public List<CategoryRawDTO> listCategoryDTO(){
        return dtoConverterService.getCategoryRAWDTOList(listCategory());
    }


    @Override
    @Transactional
    public void update(ProductDTO p) {
        this.productDAO.update(this.convertToEntity(p));
    }


    @Override
    @Transactional
    public Product findById(int id) {
        return this.productDAO.findById(id);
    }

    @Override
    @Transactional
    public ProductDTO findByIdFTO(int id){
        return this.dtoConverterService.getProductDTO(this.findById(id));
    }


    @Override
    @Transactional
    public void remove(int id) {
        this.productDAO.remove(id);
    }

    @Override
    @Transactional
    public void add(ProductDTO p) {
            this.productDAO.add(convertToEntity(p));
    }


    /**
     * adds product and adds image source to it
     * */
    @Override
    @Transactional
    public void addWithImage(ProductDTO p, MultipartFile f){
        int pid = this.productDAO.add(this.convertToEntity(p));
        String filePath = ImageHelper.uploadFileHandler(f, String.valueOf(pid));
        this.productDAO.addImageToProduct(pid, filePath);
    }

    /**
     * filters list of products by given json.
     * if any filter values are absent, then set the least or the most for correct search.
     * */
    @Override
    @Transactional
    public  List<Product> filter(String json) throws IOException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            int price = jsonNode.get("price").asText().isEmpty() ? 1000000 :  Integer.valueOf(jsonNode.get("price").asText());
            int minPlayer = jsonNode.get("minPlayer").asText().isEmpty() ? 0 : Integer.valueOf(jsonNode.get("minPlayer").asText());
            int maxPlayer = jsonNode.get("maxPlayer").asText().isEmpty() ? 40 : Integer.valueOf(jsonNode.get("maxPlayer").asText());
            int categoryId = jsonNode.get("category_id").asText().isEmpty() ? -1 : Integer.valueOf(jsonNode.get("category_id").asText());
            return this.productDAO.getProductsByFilter(price, minPlayer, maxPlayer, categoryId);
        }catch (Exception e){
            e.printStackTrace();
            return  this.productDAO.list();
        }

    }

    @Override
    @Transactional
    public Set<ProductDTO> filterDTO(String json) throws IOException {
       return dtoConverterService.getProductDTOList(filter(json));
    }


    private Product convertToEntity(ProductDTO productDTO) {
        Product product = this.findById(productDTO.getId());
        if (Objects.isNull(product)) {
            System.out.println("is null");
            product = new Product();
        }
        System.out.println(productDTO.getId());
        System.out.println(product.getId());
        //product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setWeight(productDTO.getWeight());
        product.setVolume(productDTO.getVolume());
        product.setName(productDTO.getName());
        product.setMinPlayerAmount(productDTO.getMinPlayerAmount());
        product.setMaxPlayerAmount(productDTO.getMaxPlayerAmount());
        product.setAmount(productDTO.getAmount());
        product.setCategory(this.categoryDAO.findById(productDTO.getCategory().getId()));
        if (!Objects.isNull(productDTO.getUploadFile())) {
            product.setImageSource(ImageHelper.uploadFileHandler(productDTO.getUploadFile(), productDTO.getUploadFile().getOriginalFilename()));
        }
        return product;
    }

    private ProductDTO convertToDTO(Product product){
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setUploadFile(null);
        return productDTO;
    }

    @Override
    @Transactional
    public List<Category> listCategory() {
        return  this.categoryDAO.listCategory();
    }


}
