function today() {
    return moment().format('YYYY-MM-DD');
}

function tomorrow() {
    return moment().add(1, 'days').format('YYYY-MM-DD');
}

function setDate(taskType) {
    let date;
    switch (taskType) {
        case 'TODAY':
            date = today();
            break;
        case 'TOMORROW':
            date = tomorrow();
            break;
        case 'SOMEDAY':
            let customDate = document.getElementById('customDate').value;
            date = moment(customDate).format('YYYY-MM-DD');
            break;
        default:
            date = '';
            break;
    }
    document.getElementById('taskDate').setAttribute('value', date);
}

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
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            console.log(xhr.responseText);
            //get object from json
            showTasks(tasks, taskType);
        } else {
            alert(xhr.status + ': ' + xhr.statusText);
        }
    };
    xhr.open('POST', `/showtasks?taskType=${taskType}`, true);
    xhr.send();
}

function showTasks(tasks, taskType){
    //clear table
    _.each(tasks, (task) => {
        taskRow = getTaskRow(task, taskType);
        // table add row
    })
}

function getTaskRow(task, taskType) {
    return '<tr></tr>'
}

function getCheckedTasksString($tasks) {
    let queryArray = [];
    _.each($tasks, ($task) => queryArray.push(`${$task.name}=${$task.value}`));
    // let queryArray = _.map($tasks, ($task) => `${$task.name}=${$task.value}`);
    return queryArray.join('&');
}

//set active to the chosen taskType
$(document).ready(function () {
    function setActive(taskType) {
        document.querySelector(`#taskTypeSwitcher button[value='${taskType}']`).classList.add('active')
    }

    let taskType = document.getElementById('currentTaskType');
    if (taskType){
        setActive(taskType.value)
    }

});

$('body')
    .on('change', '#select-all', function () {
        const isChecked = this.checked;
        $elems = $('input[name=checkedTask]');
        _.each($elems, ($elem) => $elem.checked = isChecked)
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
    });