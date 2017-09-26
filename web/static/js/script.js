const tasksTable = document.getElementById('tasksTable');
const taskActions = document.getElementById('taskActions');
const TASK_TYPES = {
    TODAY: 'TODAY',
    TOMORROW: 'TOMORROW',
    SOMEDAY: 'SOMEDAY',
    FIXED: 'FIXED',
    RECYCLE_BIN: 'RECYCLE_BIN',
};
let currentTaskType = document.getElementById('currentTaskType').value || TASK_TYPES.TODAY;

function getCheckedTasksString($tasks) {
    let queryArray = [];
    _.each($tasks, ($task) => queryArray.push(`${$task.name}=${$task.value}`));
    // let queryArray = _.map($tasks, ($task) => `${$task.name}=${$task.value}`);
    return queryArray.join('&');
}

function toggleActive(taskType){
    document.querySelector(`#taskTypeSwitcher button[value='${currentTaskType}']`).classList.remove('active');
    document.querySelector(`#taskTypeSwitcher button[value='${taskType}']`).classList.add('active');
}

function setDate(taskType) {
    let date;
    switch (taskType) {
        case TASK_TYPES.TODAY:
            date = moment().format('YYYY-MM-DD');
            break;
        case TASK_TYPES.TOMORROW:
            date = moment().add(1, 'days').format('YYYY-MM-DD');
            break;
        case TASK_TYPES.SOMEDAY:
            let customDate = document.getElementById('customDate').value;
            date = moment(customDate).format('YYYY-MM-DD');
            break;
        default:
            date = '';
            break;
    }
    document.getElementById('taskDate').setAttribute('value', date);
}

//moveTasks function
function updateTasks(action, additionalParams='') {
    let $checkedTasks = $('input[name=checkedTask]:checked');
    let queryParams = getCheckedTasksString($checkedTasks);

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            _.each($checkedTasks, ($task) => $task.parentNode.parentNode.remove());
        } else {
            alert(xhr.status + ': ' + xhr.statusText);
    }
    };

    xhr.open('POST', `/${action}?${queryParams}&${additionalParams}`, true);
    xhr.send();
}

function loadTasks(taskType) {
    toggleActive(taskType);
    currentTaskType = taskType;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            const tasks = JSON.parse(xhr.responseText);
            showTasks(tasks, taskType);
            showTaskActions(taskType)
        } else {
            alert(xhr.status + ': ' + xhr.statusText);
        }
    };
    xhr.open('POST', `/showtasks?taskType=${taskType}`, true);
    xhr.send();
}

function showTasks(tasks, taskType){
    const thead = document.createElement('thead');
    thead.innerHTML = `
        <tr>
            <th><input type="checkbox" id="select-all" title="Select All"></th>
            <th>Task Name</th>
            ${currentTaskType === TASK_TYPES.SOMEDAY ? '<th>Task Date</th>' : ''}
            <th colspan="2">Task File</th>
        </tr>
    `;

    const tbody = document.createElement('tbody');
    _.each(tasks, (task) => {
        const taskRow = getTaskRow(task, taskType);
        tbody.appendChild(taskRow);
    });

    tasksTable.innerHTML = '';
    tasksTable.appendChild(thead);
    tasksTable.appendChild(tbody);
}

function getTaskRow(task, taskType) {
    const row = document.createElement('tr');
    const dateCellHTML = taskType === 'SOMEDAY'
        ? `<td id="taskDate" class="text-cell">
                <span>${task.taskDate}</span>
            </td>`
        : '';
    const fileCellHTML = task.fileName
        ? `<div class="row">
                <div class="col"><span><a
                        href="/downloadFile?fileName=${task.fileName}">${task.shortFileName}</a></span>
                </div>
                <div class="col">
                    <form method="post" action="/deleteFile">
                        <button class="btn btn-danger" name="fileName" value="${task.fileName}">Delete File</button>
                    </form>
                </div>
            </div>`
        : `<form method="post" action="/uploadFile" enctype="multipart/form-data">
                <div class="row">
                    <div class="col">
                        <label class="custom-file">
                            <input data-toggle="custom-file"
                                   data-target="#upload-file-${task.taskId}"
                                   type="file" name="file" class="custom-file-input">
                            <span id="upload-file-${task.taskId}"
                                  class="custom-file-control custom-file-name"
                                  data-content="Choose file..."></span>
                        </label>
                    </div>
                    <div class="col">
                        <button class="btn btn-secondary" name="taskId" value="${task.taskId}">Upload File</button>
                    </div>
                </div>
            </form>`
    ;
    row.innerHTML = `
        <td class="check-box-cell">
            <input type="checkbox" name="checkedTask" value="${task.taskId}">
        </td>
        <td class="text-cell">
            <span>${task.taskName}</span>
        </td>
        ${dateCellHTML}
        <td>${fileCellHTML}</td>
    `;

    return row;
}

function showTaskActions(taskType) {
    const actions = document.createElement('div');
    let actionsHTML = '';
    switch (taskType){
        case TASK_TYPES.TODAY:
        case TASK_TYPES.TOMORROW:
        case TASK_TYPES.SOMEDAY:
            actionsHTML = `
                <form class="form-inline" method="post" action="/jsp/addTask.jsp">
                    <div class="btn-group">
                        <button class="btn btn-primary">Add Task</button>
                        <button class="btn btn-primary" type="button" onclick="updateTasks('movetask', 'moveType=MOVE_TO_FIXED')">Move To Fixed</button>
                        <button class="btn btn-primary" type="button" onclick="updateTasks('movetask', 'moveType=MOVE_TO_BIN')">Move To Bin</button>
                    </div>
                </form>
            `;
            break;
        case TASK_TYPES.FIXED:
            actionsHTML = `
                <div class="btn-group">
                    <button class="btn btn-primary" onclick="updateTasks('movetask', 'moveType=MOVE_TO_BIN')">Move To Bin</button>
                    <button class="btn btn-primary" onclick="updateTasks('movetask', 'moveType=RESTORE_FIXED')">Restore Task</button>
                </div>
            `;
            break;
        case TASK_TYPES.RECYCLE_BIN:
            actionsHTML = `
                <div class="btn-group">
                    <button class="btn btn-primary" onclick="updateTasks('movetask', 'moveType=RESTORE')">Restore Task</button>
                    <button class="btn btn-danger" onclick="updateTasks('deleteTask')">Delete Task</button>
                </div>
            `;
            break;
        default:
            actionsHTML = '';
    }
    actions.innerHTML = actionsHTML;

    taskActions.innerHTML = '';
    taskActions.appendChild(actions);
}

$('body')
    .on('change', '#select-all', function () {
        const isChecked = this.checked;
        const $elems = $('input[name=checkedTask]');
        _.each($elems, ($elem) => {$elem.checked = isChecked});
    })
    .on('change', 'input[type="file"][data-toggle="custom-file"]', function () {
        const $input = $(this);
        const target = $input.data('target');
        const $target = $(target);

        if (!$target.length)
            return console.error('Invalid target for custom file', $input);

        if (!$target.attr('data-content'))
            return console.error('Invalid `data-content` for custom file target', $input);

        // set original content so we can revert if user deselects file
        if (!$target.attr('data-original-content'))
            $target.attr('data-original-content', $target.attr('data-content'));

        const input = $input.get(0);

        let name = _.isObject(input)
        && _.isObject(input.files)
        && _.isObject(input.files[0])
        && _.isString(input.files[0].name) ? input.files[0].name : $input.val();

        if (_.isNull(name) || name === '')
            name = $target.attr('data-original-content');

        $target.attr('data-content', name);
    })
;

$(document).ready(function(){
    loadTasks(currentTaskType);
});
