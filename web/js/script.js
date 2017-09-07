function today() {
    return moment().format('YYYY-MM-DD');
}

function tomorrow() {
    return moment().add(1, 'days').format('YYYY-MM-DD');
}

function setDate(taskType){
    let date;
    switch(taskType){
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

function moveTask(moveType) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) {
            return;
        }
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        }
        else {
            deleteCheckedTasks();
        }

    };
    let elems = document.querySelectorAll('input[name=checkedTask]:checked');
    let queryArray = [];
    elems.forEach((elem) => queryArray.push(`${elem.name}=${elem.value}`));
    let queryParams = queryArray.join('&');

    let taskAction = `&moveType=${moveType}`;
    xhr.open('POST', `/movetask?${queryParams}${taskAction}`, true);
    xhr.send();

    function deleteCheckedTasks() {
        elems.forEach((elem) => elem.parentNode.parentNode.remove());
    }
}

$(document).ready(function(){
    function setActive(taskType){
        document.querySelector(`#taskTypeSwitcher button[value='${taskType}']`).classList.add('active')
    }
    setActive(document.querySelector('#currentTaskType').value)

});

$('body')
    .on('change', '#select-all', function () {
        const isChecked = this.checked;
        $('input[name=checkedTask]').each(function() {
            this.checked = isChecked;
        });
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