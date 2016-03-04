/**
 * Created by zzt on 3/3/16.
 *
 */


function tableInit() {
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
            listAction: 'UserList'
        },
        fields: {
            uid: {
                title: 'User Id',
                width: '30%',
                key: true,
                list: true,
                edit: true
            },
            balance: {
                title: 'balance',
                width: '20%',
                edit: true
            }
        }
    });
    balance.jtable('load');

    var order = $('#order-detail');
    order.jtable({
        title: 'User today order detail',
        defaultSorting: 'rank ASC',
        actions: {
            listAction: 'BranchUserReserveDetailList?userId=' + readGet()['userId'],
            deleteAction: 'BranchUserReserveDetailDelete'
        },
        fields: {
            dessert: {
                title: 'Dessert',
                width: '20%',
                display: function (data) {
                    return data.record.dessert.name;
                }
            },
            num: {
                title: 'Number',
                width: '30%'
            },
            price: {
                title: 'Price',
                width: '30%'
            },
            rdid: {
                title: 'Reservation Detail Id',
                key: true,
                list: false
            }
        }
    });
    order.jtable('load');
}


function logResponse(response) {
    console.log("Response: " + response);
}

function sendMsg() {
    var lineData = $('#balanceTable').jtable('selectedRows');
    $.each(
        lineData,
        function (index, line) {
            var user = {
                uid: line.attributes['data-record-key'].value,
                msg: $('#msg').text()
            };
            var dialogSetting = {
                buttons: {
                    "Get it": function () {
                        $(this).dialog("close");
                    }
                }
            };
            $.ajax({
                type: "POST",
                url: "MsgSend",
                data: user,
                success: function (response) {
                    $('#doneSending').dialog(dialogSetting);
                    console.log(response);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $('#errorSending').dialog(dialogSetting);
                }
            });
        }
    );
    $('#msgDialog').dialog("close");
}

function pay() {
    var money = $('#money').text();
    $.post('PayMoney', {money: money, type: payType}, logResponse);
    $(this).dialog("close");
}

function getUserInfo(e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        // have to prevent default!!
        e.preventDefault();
        window.location.replace('/dessert/SearchUser?userId=' + this.value);
    }
}