<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zzt
  Date: 2/19/16
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>branch waiter</title>

    <link rel="stylesheet" href="../styles/account-content.css">
    <link rel="stylesheet" href="../styles/main-header.css">


    <link rel="stylesheet" href="../fonts/font-awesome-4.4.0/css/font-awesome.min.css"/>


    <!-- jTable Metro theme -->
    <link href="../scripts/jtable.2.4.0/themes/metro/blue/jtable.css" rel="stylesheet" type="text/css"/>
    <link href="../scripts/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet"
          type="text/css"/>

</head>
<body onload="
addListChosenListener('side_nav_list', 'tabbed-block');
">

<header>
    <section id="main-header">
        <img src="../images/yellow-pin.png" id="logo">

        <p id="app-name"><a href="#">Staff Id: ${sessionScope.sid}</a></p>


        <form>
            <p class="action">
                <a href="<s:url action='InnerLogin_logOut'/>" class="fa fa-user"> log out</a>
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
                    <input type="search" class="search-input" placeholder="user id" pattern="\d+" id="search">
                </label>
            </form>

            <div>
                <h3>User public information</h3>
                <section class="personal-info flex-container-large">
                    <div class="avatar-wrapper none">
                        <img id="avatar" class="resize" src="../images/user.png" alt="a user">

                    </div>
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
                            <li>${userRankDes}</li>
                        </ul>

                        <div id="apply" title="Message dialog" class="none">
                            <label>Money:
                                <input type="text" id="applyMoney" value="${toPay}">
                            </label>
                            <div class="horizontal-center">
                                <input type="submit" value="User card" onclick="payType = 'card';pay()"/>
                                <pre>  </pre>
                                <input type="submit" value="Other ways" onclick="payType = 'other';pay()"/>
                            </div>
                        </div>
                    </div>

                </section>

                <h3>User order in your branch</h3>
                <h4 style="display: inline">Reservation Id: </h4><h4 id="rid" style="display: inline;">${rid}</h4>
                <h4>Should pay: ${toPay}</h4>
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
                    <input type="submit" value="User card" onclick="payType = 'USER_CARD';$('#payDialog').dialog();"/>
                    <pre>  </pre>
                    <input type="submit" value="Other ways" onclick="payType = 'OTHER';$('#payDialog').dialog();"/>
                </div>
            </div>
        </div>

        <div class="container" id="message">

            <div id="balanceTable"></div>


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
            <div id="doneSending" class="none" title="Success">
                <p>Sending message done</p>
            </div>
            <div id="errorSending" class="none" title="Error">
                <p>Sending message meet error</p>
            </div>
        </div>

    </div>
</div>

<script type="application/javascript" src="../scripts/jquery/dist/jquery.min.js"></script>
<script type="application/javascript" src="../scripts/chosen.js"></script>

<!-- jTable script file. -->
<script src="../scripts/jquery-ui-1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<script src="../scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript"></script>
<script type="application/javascript" src="../scripts/otherWaiter.js"></script>
<script type="text/javascript">
    $(tableInit);
    document.getElementById('search').onkeypress = getUserInfo;
</script>

</body>
</html>
