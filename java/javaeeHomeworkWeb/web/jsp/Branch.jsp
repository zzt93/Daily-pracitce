<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/14/16
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../styles/sign-head.css" type="text/css" rel="stylesheet">
    <link href="../styles/sign-content.css" type="text/css" rel="stylesheet">
    <link href="../styles/anchor.css" type="text/css" rel="stylesheet">

    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../styles/main-header.css">
    <link rel="stylesheet" href="../styles/branches.css">
    <link rel="stylesheet" href="../styles/util.css">
    <link rel="stylesheet" href="../styles/dessert.css">
    <link rel="stylesheet" href="../styles/lightBox.css">

    <!-- jTable Metro theme -->
    <link href="../scripts/jtable.2.4.0/themes/lightcolor/gray/jtable.css" rel="stylesheet" type="text/css"/>
    <link href="../scripts/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet"
          type="text/css"/>

    <title>Branch ${branchNum}</title>
    <s:head/>
</head>
<body>

<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="<s:url action="Branches"/> ">Dessert</a></p>

        <form>
            <p class="action">
                <a href="<s:url action='Login_input'/>" class="fa fa-user"> log out</a>
            </p>

            <p class="action">
                <a href="<s:url action='Account'/> " class="fa fa-home"> ${sessionScope.userName}</a>
            </p>
        </form>
        <br>
    </section>
</header>

<div id="headline">
    <div class="branch-container ">

        <header>
            <h1>Dessert house</h1>

            <h3>Branch ${branchNum}</h3>

        </header>
        <!-- Elements after a floating element will flow around it.
         To avoid this, use the clear property.
        The clear property specifies on which sides of an element
         floating elements are not allowed to float:-->
    </div>
</div>

<div id="example">
    <div class="branch-container flex-container">

        <nav class="flex1 right-split">
            <h5>Phone Number:</h5>
            <h3>025-83593186</h3>
            <h5>Address:</h5>
            <h3>NanJing, NJU</h3>

            <a class="dessert-div"
               href="http://cn.bing.com/ditu/?FORM=Z9LH4#JnE9LiUyNXU1MzU3JTI1dTRlYWMlMjV1NTkyNyUyNXU1YjY2JTI1dTlmMTMlMjV1Njk3YyU3ZXNzdC4wJTdlcGcuMSZiYj0zMi4wNTkwOTEwNTM4NTk2JTdlMTE4Ljc5MDQ1NzA3NDg4NiU3ZTMyLjA0OTg4ODc3ODA5NTMlN2UxMTguNzY4Njc3NTM3Njg1">
                <img class="map" src="../images/map.png">
            </a>

            <h3>Create order</h3>
            <div id="drop">
                <div class="vertical-center" style="height: 80%;">
                    drag dessert and drop here to make order
                </div>
            </div>


        </nav>

        <div class="flex2">
            <h3>Orders</h3>
            <div id="previous-order"></div>

            <h3>Desserts</h3>
            <div>
                <c:forEach items="${plans}" var="plan">
                    <h4>${plan.pdate}</h4>
                    <c:forEach items="${plan.details}" var="detail">
                        <div class="dessert-div">
                            <img class="dessert" src="../images/${detail.did}.jpg">
                        </div>
                    </c:forEach>
                </c:forEach>

                <%--TODO delete following--%>
                <h4>2016-02-22</h4>
                <c:forEach items="${plan.details}" var="detail">
                    <div class="dessert-div">
                        <img class="dessert" src="../images/${detail.did}.jpg">
                    </div>
                </c:forEach>

                <div class="dessert-div">
                    <img class="dessert" src="../images/1.jpg">
                </div>
                <div class="dessert-div">
                    <img class="dessert" src="../images/2.jpg">
                </div>
                <div class="dessert-div">
                    <img class="dessert" src="../images/3.jpg">
                </div>
                <h4>2016-02-23</h4>
                <c:forEach items="${plan.details}" var="detail">
                    <div class="dessert-div">
                        <img class="dessert" src="../images/${detail.did}.jpg">
                    </div>
                </c:forEach>

                <div class="dessert-div">
                    <img class="dessert" src="../images/1.jpg">
                </div>
                <div class="dessert-div">
                    <img class="dessert" src="../images/7.jpg">
                </div>
            </div>

            <h3>New order</h3>
            <div id="current-order"></div>

            <div class="horizontal-center">
                <s:form action="BranchReservePay">
                    <s:submit value="Submit your order"/>
                </s:form>
            </div>
        </div>

    </div>
</div>

<div id="date-box" title="Date reminding" class="none">
    <p class="fa fa-2x fa-warning">can't choose desserts in different date in same order</p>
</div>

<%@include file="../html/footer.html" %>


<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<!-- jTable script file. -->
<script src="../scripts/jquery-ui-1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<script type="application/javascript" src="../scripts/drag.js"></script>
<script src="../scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var order = $('#previous-order');
        order.jtable({
            title: 'Your orders in this branch',
            paging: true,
            pageSize: 6,
            actions: {
                listAction: 'ReserveList',
                deleteAction: 'ReserveDelete'
            },
            fields: {
                rollNo: {
                    title: 'Reservation Id',
                    width: '30%',
                    key: true,
                    list: true,
                    create: true
                },
                studentName: {
                    title: 'Branch',
                    width: '30%',
                    edit: false
                },
                department: {
                    title: 'Department',
                    width: '30%',
                    edit: true
                },
                rank: {
                    title: 'Rank',
                    width: '20%',
                    edit: true
                }
            }
        });
        order.jtable('load');


        initDragDrop();


        var currentOrder = $('#current-order');
        currentOrder.jtable({
            title: 'Current order',
            // TODO remove paging
            paging: true,
            pageSize: 6,
            actions: {
                listAction: 'ReserveList',
                deleteAction: 'BranchReserveDelete',
                updateAction: 'BranchReserveEdit'
            },
            fields: {
                rollNo: {
                    title: 'Dessert Id',
                    width: '30%',
                    key: true,
                    list: true,
                    create: true
                },
                studentName: {
                    title: 'Price',
                    width: '30%',
                    edit: false
                },
                department: {
                    title: 'Number',
                    width: '30%',
                    edit: true
                }
            }
        });
        currentOrder.jtable('load');
    });

</script>


</body>
</html>

