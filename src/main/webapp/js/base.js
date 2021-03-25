/*
 * The MIT License
 * Copyright © 2014-2021 Stefan Frei
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


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
    const row = document.createElement('div');
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
    const cell = document.createElement('div');
    cell.classList = 'cell';
    return cell;
};


const createRandomChar = () => {
    return String.fromCharCode(
        UC_BOUNDS_LOWER + Math.random() * (UC_BOUNDS),
    );
};


const doEffect = () => {
    const matrix = document.getElementById('matrix-wrapper');
    const row = matrix.lastChild;
    row.remove();
    matrix.prepend(row);
};


const stopMatrix = () => {
    window.clearInterval(interval_id);
}


/* Listener-Code */
const initMatrix = () => {
    const matrix = document.getElementById('matrix-wrapper');
    for (id of range(1, NUM_ROWS)) {
        matrix.append(
            createRow(id),
        );
    }
};
window.addEventListener('DOMContentLoaded', initMatrix);


const startMatrix = () => {
    interval_id = window.setInterval(doEffect, 50);
};
// document.addEventListener('readystatechange', startMatrix);
/* End */
