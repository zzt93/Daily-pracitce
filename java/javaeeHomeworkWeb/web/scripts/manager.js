/**
 * Created by zzt on 3/2/16.
 *
 */

var planStateOptions = {0: 'New', 1: 'Approved', 2: 'Rejected'};
function tables() {
    var plan = $('#plan');
    plan.jtable({
        title: 'Plan to view',
        paging: true,
        pageSize: 6,
        selecting: true, //Enable selecting
        multiselect: true, //Allow multiple selecting
        selectingCheckboxes: true, //Show checkboxes on first column
        selectOnRowClick: false,
        actions: {
            listAction: 'PlanManagerList',
            updateAction: 'PlanManagerUpdate'
        },
        fields: {
            planId: {
                title: 'Plan Id',
                width: '30%',
                key: true,
                list: false
            },
            planState: {
                title: 'State',
                width: '20%',
                edit: true,
                options: planStateOptions
            },
            pdate: {
                title: 'Plan for ',
                width: '20%',
                edit: false
            },
            branch: {
                title: 'Branch',
                width: '30%',
                edit: false,
                options: 'BranchOptions',

                display: function (data) {
                    return data.record.branch.addr;
                }
            },
            details: {
                title: '',
                width: '2%',
                edit: false,
                create: false,
                display: function (plan) {
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/more.png" title="Show plan detail" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#plan').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'Plan ' + plan.record.planId + ' - details',
                                actions: {
                                    listAction: 'PlanDetailList?planId=' + plan.record.planId
                                },
                                fields: {
                                    pdId: {
                                        title: 'Plan detail id',
                                        width: '30%',
                                        key: true,
                                        list: false
                                    },
                                    dessert: {
                                        title: 'Dessert',
                                        width: '20%',
                                        display: function (data) {
                                            return data.record.dessert.name;
                                        }
                                    },
                                    price: {
                                        title: 'Dessert price'
                                    },
                                    num: {
                                        title: 'Number',
                                        width: '30%',
                                        edit: true
                                    }
                                }
                            },
                            function (data) { //opened handler
                                data.childTable.jtable('load');
                            });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            }
        }

    });
    plan.jtable('load');

    var branch = $('#branch');
    branch.jtable({
        title: 'Branch list',
        actions: {
            listAction: 'BranchList',
            deleteAction: 'BranchDelete',
            updateAction: 'BranchUpdate',
            createAction: 'BranchAdd'
        },
        fields: {
            bid: {
                title: 'Branch Id',
                key: true,
                list: true
            },
            addr: {
                title: 'Address',
                edit: true
            }
        }
    });
    branch.jtable('load');

    var balance = $('#userCard');
    balance.jtable({
        title: 'User balance list',
        paging: true,
        pageSize: 6,
        selecting: true, //Enable selecting
        multiselect: true, //Allow multiple selecting
        selectingCheckboxes: true, //Show checkboxes on first column
        actions: {
            listAction: 'UserCardList'
        },
        fields: {
            uid: {
                title: 'User Id',
                width: '20%',
                key: true,
                list: true
            },
            state: {
                title: 'Card state',
                width: '30%',
                options: {0: 'Let to activate', 1: 'Activated', 2: 'Suspend', 3: 'Canceled'}
            },
            balance: {
                title: 'Balance',
                width: '30'
            }
        }
    });
    balance.jtable('load');
}

function updatePlanState(state) {
    var lineData = $('#plan').jtable('selectedRows');
    $.each(
        lineData,
        function (index, line) {
            var newPlan = {
                planId: line.attributes['data-record-key'].value,
                planState: state
            };

            $('#plan').jtable('updateRecord',
                {
                    record: newPlan
                }
            );
        }
    );
}

function approvePlan() {
    updatePlanState(1);
}

function rejectPlan() {
    updatePlanState(2);
}

function getChartData() {
    $.get('ManagerAgeChart', function (response) {
        produceBarChart('ageChart', response['ageChartData']);
        produceBarChart('profitChart', response['profitChartData']);
        produceTwoAxisChart('dessertChart', response['dessertChartData']);
    });
}
