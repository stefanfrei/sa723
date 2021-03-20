const NUM_ROWS = 40;
const CELLS_PER_ROW = 40;
const UC_BOUNDS_LOWER = 0x30A0;
const UC_BOUNDS_UPPER = 0x30FF;
const UC_BOUNDS = UC_BOUNDS_UPPER - UC_BOUNDS_LOWER+1

var interval_id;


function* range(start, end) {
    for (let i = start; i <= end; i += 1) {
        yield i;
    }
}


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
        UC_BOUNDS_LOWER + Math.random() * (UC_BOUNDS),
    );
};


const doEffect = () => {
    const row = window.MATRIX.lastChild;
    row.remove();
    window.MATRIX.prepend(row);
};



/* Listener-Code */
const initMatrix = () => {
    window.MATRIX = document.querySelector('#wrapper');
    for (id of range(1, NUM_ROWS)) {
        window.MATRIX.append(
            createRow(id),
        );
    }
};
window.addEventListener('DOMContentLoaded', initMatrix);


const runMatrix = () => {
    interval_id = window.setInterval(doEffect, 50);
};
document.addEventListener('readystatechange', runMatrix);
/* End */