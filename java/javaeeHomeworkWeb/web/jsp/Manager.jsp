<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/22/16
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Main branch waiter</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>

    <!-- jTable Metro theme -->
    <link href="../scripts/jtable.2.4.0/themes/lightcolor/gray/jtable.css" rel="stylesheet" type="text/css"/>
    <link href="../scripts/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet"
          type="text/css"/>

</head>
<body onload="
addListChosenListener('side_nav_list', 'tabbed-block');
">

<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="Branches.jsp">Dessert</a></p>

        <form>
            <p class="action">
                <a href="<s:url action='InnerLogin_input'/>" class="fa fa-user"> log out</a>
            </p>

        </form>
        <br>
    </section>
</header>

<div id="main-container" class="flex-container-large">
    <nav id="side_nav" class="flex-none">
        <ul id="side_nav_list">
            <li>
                <a href="#">Plan </a>
            </li>
            <li>
                <a href="#">Branches </a>
            </li>
            <li>
                <a href="#">Statistic </a>
            </li>
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">

        <div class="container">
            <div id="plan"></div>
            <div class="horizontal-center">
                <input type="submit" onclick="approve()" value="Approve"/>
            </div>
        </div>

        <div class="container">
            <div id="branch"></div>
<%--            <div class="horizontal-center">
                <input type="submit" onclick="approve()" value="Approve"/>
            </div>--%>
        </div>

        <div class="container">

        </div>

    </div>
</div>


<script type="application/javascript" src="../scripts/chosen.js"></script>
<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<!-- jTable script file. -->
<script src="../scripts/jquery-ui-1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<script src="../scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var plan = $('#plan');
        plan.jtable({
            title: 'PlanAction list',
            paging: true,
            pageSize: 6,
            selecting: true, //Enable selecting
            multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column
            actions: {
                listAction: 'PlanManagerList',
                deleteAction: 'PlanDelete',
                updateAction: 'PlanUpdate'
            },
            fields: {
                rollNo: {
                    title: 'PlanAction Id',
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
        plan.jtable('load');

        var branch = $('#branch');
        branch.jtable({
            title: 'Branch list',
            paging: true,
            pageSize: 6,
            actions: {
                listAction: 'BranchList',
                deleteAction: 'BranchDelete',
                updateAction: 'BranchUpdate'
            },
            fields: {
                rollNo: {
                    title: 'Branch Id',
                    width: '30%',
                    key: true,
                    list: true,
                    create: true
                },
                studentName: {
                    title: 'Address',
                    width: '30%',
                    edit: false
                }
            }
        });
        branch.jtable('load');

        function approve() {
            var lineData = $('#plan').jtable('selectedRows');
            $.post('PlanApprove', lineData, function (response) {
                        console.log("Response: " + response);
                    }
            );
        }
    });

</script>

</body>
</html>
