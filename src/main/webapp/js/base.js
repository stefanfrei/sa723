$(document).ready(function () {
    const target = document.querySelector('#wrapper');
    for (i of range(1, 1600)) {
        createDiv(target);
    }
    //setInterval(doEffect(target), 500);
});

function* range(start, end) {
    for (let i = start; i <= end; i += 1) {
        yield i;
    }
}

const createDiv = (target, prepend = false) => {
    const div = document.createElement('DIV');
    div.innerHTML = String.fromCharCode(0x30A0 + Math.random() * (0x30FF-0x30A0+1));
    prepend ? target.prepend(div) : target.append(div);
}

const doEffect = (target) => {
    flushLastLine(target);
    prependLine(target);
};

const prependLine = (target) => {
    for (i of range(1, 40)) {
        createDiv(target, true);
    }
};

flushLastLine = (target) => {
    for (i of range(1, 40)) {
        target.lastChild.remove();
    }
};
