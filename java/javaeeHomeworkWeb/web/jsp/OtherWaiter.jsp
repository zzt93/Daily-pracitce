<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/19/16
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>branch waiter</title>

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
                <a href="#">Sale </a>
            </li>
            <li>
                <a href="#">Message </a>
            </li>
        </ul>
    </nav>

    <div id="tabbed-block" class="flex1">

        <div class="container">
            <form class="inner-search">
                <label class="fa fa-search">
                    <input type="search" class="search-input" placeholder="user id">
                </label>
            </form>

            <div>
                <h3>User public information</h3>
                <section class="personal-info flex-container-large">
                    <div class="avatar-wrapper none">
                        <img id="avatar" class="resize" src="../images/user.png" alt="a user">

                    </div>
                    <div id="change-picture-progress">&nbsp;</div>
                    <div class="flex1 info-part">
                        <label for="DisplayName">User name
                            <br>
                            <input id="DisplayName" value="${user.name}" maxlength="30" tabindex="1"
                                   data-site="Tony" type="text">
                        </label>

                        <br>
                        <label>Location
                            <br>
                            <input id="Location" value="${account.addr}" maxlength="100"
                                   tabindex="3" data-site="Nanjing, China" type="text">
                        </label>
                        <br>
                    </div>

                    <div class="flex1 info-part">
                        <h4> User card</h4>
                        <ul id="roles">
                            <li>silver</li>
                        </ul>
                    </div>

                </section>

                <h3>User order</h3>
                <div id="order-detail"></div>
                <div id="payDialog" title="Pay dialog" class="none">
                    <label>Money:
                        <input type="text" id="money">
                    </label>
                    <div class="horizontal-center">
                        <input type="submit" onclick="pay()" value="Pay"/>
                    </div>
                </div>


                <div class="horizontal-center">
                    <input type="submit" value="User card" onclick="payType = 'card';$('#payDialog').dialog();"/>
                    <pre>  </pre>
                    <input type="submit" value="Other ways" onclick="payType = 'other';$('#payDialog').dialog();"/>
                </div>
            </div>
        </div>

        <div class="container" id="message">

            <div id="balanceTable"></div>

            <%--<div class="light-box" id="sendMsg">
                            <div style="height: 200px;">
                                <label>Message:
                                    <textarea id="msg" style="height: 150px">Sir, your balance is too low.
                                    </textarea>
                                </label>
                                <input type="submit" onclick="sendMsg()" value="Send"/>
                            </div>
                        </div>--%>
            <div class="horizontal-center">
                <input type="submit" onclick="$('#msgDialog').dialog();" value="Send notification"/>
            </div>

            <div id="msgDialog" title="Message dialog" class="none">
                <label>Message:
                    <textarea id="msg" style="height: 150px">Sir, your balance is too low.</textarea>
                </label>
                <div class="horizontal-center">
                    <input type="submit" onclick="sendMsg()" value="Send"/>
                </div>
            </div>
        </div>

    </div>
</div>

<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/chosen.js"></script>

<!-- jTable script file. -->
<script src="../scripts/jquery-ui-1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<script src="../scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var balance = $('#balanceTable');
        balance.jtable({
            title: 'User balance list',
            paging: true,
            pageSize: 6,
            selecting: true, //Enable selecting
            multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column
            defaultSorting: 'rank ASC',
            actions: {
                listAction: 'ReserveList'
            },
            fields: {
                rollNo: {
                    title: 'User Id',
                    width: '30%',
                    key: true,
                    list: true,
                    create: true,
                    edit: true
                },
                rank: {
                    title: 'balance',
                    width: '20%',
                    edit: true
                }
            }
        });
        balance.jtable('load');

        var order = $('#order-detail');
        order.jtable({
            title: 'User order',
            selecting: true, //Enable selecting
            multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column
            defaultSorting: 'rank ASC',
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
    });

    function logResponse(response) {
        console.log("Response: " + response);
    }

    function sendMsg() {
        var lineData = $('#balanceTable').jtable('selectedRows');
        $.post('MsgSend', lineData, logResponse);
        $( this ).dialog( "close" );
    }

    function pay() {
        var money = $('#money').text();
        $.post('PayMoney', {money:money, type:payType}, logResponse);
        $( this ).dialog( "close" );
    }

</script>

</body>
</html>
