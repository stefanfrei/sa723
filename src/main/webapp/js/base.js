const NUM_ROWS = 40;
const CELLS_PER_ROW = 40;
const UC_BOUNDS = {
    lower : 0x30A0,
    upper : 0x30FF,
};

function* range(start, end) {
    for (let i = start; i <= end; i += 1) {
        yield i;
    }
}

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
}

const createCell = () => {
    const cell = document.createElement('DIV');
    cell.classList = 'cell';
    cell.innerHTML = createRandomChar();
    return cell;
}

const createRandomChar = () => {
    return String.fromCharCode(
        UC_BOUNDS.lower + Math.random() * (UC_BOUNDS.upper-UC_BOUNDS.lower+1),
    );
};

const doEffect = (target) => {
    flushLastLine(target);
    prependLine(target);
};

const prependLine = (target) => {
    for (i of range(1, 40)) {
        createDiv(target, true);
    }
};

const flushRow = (target) => {
    for (i of range(1, 40)) {
        target.lastChild.remove();
    }
};

window.addEventListener('DOMContentLoaded', initMatrix);
