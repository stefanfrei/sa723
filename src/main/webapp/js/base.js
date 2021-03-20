$(document).ready(function () {
    const target = document.querySelector('#wrapper');
    for (let i=0; i<1600; i+=1) {
        createDiv(target);
    }

    //setInterval(doEffect(target), 500);
});

createDiv = (target, prepend = false) => {
    const div = document.createElement('DIV');
    div.innerHTML = String.fromCharCode(0x30A0 + Math.random() * (0x30FF-0x30A0+1));
    console.log(target);
    if (prepend) {
        target.prepend(div);
    } else {
        target.append(div);
    }
}

doEffect = (target) => {
    flushLastLine(target);
    prependLine(target);
};

prependLine = (target) => {
    for (let i = 0; i < 40; i =+ 1) {
        createDiv(target, true);
    }
};

flushLastLine = (target) => {
    for (let i = 0; i < 40; i += 1) {
        target.lastChild.remove();
    }
};
