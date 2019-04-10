<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-3 navigation" style="position: fixed; overflow: auto; margin-top: 25vh">
    <div class="row filter content">
        <div class="col">
            <form id="filterForm" action="/filter">
                <div class="form-row">
                    <div class="form-group col-6">
                        <select id="categoryFilter" class="form-control">
                            <c:forEach items="${listCategories}" var="cat">
                                <option value="${cat.id}">${cat.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-6">
                        <input type="number" class="form-control" id="priceFilter" name="maxCost"
                               aria-describedby="maxCostHelp">
                        <small class="form-text text-muted">Max price</small>
                    </div>

                    <div class="form-group col-6">
                        <input type="number" class="form-control" id="minPlayerAmountFilter" name="maxCost"
                               aria-describedby="maxCostHelp">
                        <small class="form-text text-muted">Min player amount</small>
                    </div>
                    <div class="form-group col-6">
                        <input type="number" class="form-control" id="maxPlayerAmountFilter" name="maxCost"
                               aria-describedby="maxCostHelp">
                        <small class="form-text text-muted">Max player amount</small>
                    </div>
                    <div class="col-6">
                        <button id="applyFilter" class="btn btn-lg btn-primary orderbutton">Filter</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>