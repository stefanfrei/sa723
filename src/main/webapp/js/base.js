const NUM_ROWS = 40;
const CELLS_PER_ROW = 40;
const UC_BOUNDS = {
    lower : 0x30A0,
    upper : 0x30FF,
};

var interval_id;

function* range(start, end) {
    for (let i = start; i <= end; i += 1) {
        yield i;
    }
}

const runMatrix = () => {
    interval_id = window.setInterval(doEffect, 50);
};

const initMatrix = () => {
    const target = document.querySelector('#wrapper');
    for (id of range(1, NUM_ROWS)) {
        target.append(
            createRow(id),
        );
    }
};

const createRow = (id) => {
    const row = document.createElement('DIV');
    row.id = id;
    row.classList = 'cfx';
    for (i of range(1, CELLS_PER_ROW)) {
        row.append(
            createCell(),
        );
    }
    return row;
};

const createCell = () => {
    const cell = document.createElement('DIV');
    cell.classList = 'cell';
    cell.innerHTML = createRandomChar();
    return cell;
};

const createRandomChar = () => {
    return String.fromCharCode(
        UC_BOUNDS.lower + Math.random() * (UC_BOUNDS.upper-UC_BOUNDS.lower+1),
    );
};

const doEffect = () => {
    console.log('effect');
    const matrix = document.querySelector('.matrix');
    const row = matrix.lastChild;
    row.remove();
    matrix.prepend(row);
};

window.addEventListener('DOMContentLoaded', initMatrix);

document.addEventListener('readystatechange', runMatrix);
