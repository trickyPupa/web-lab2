const url = "controller"

// вспомогательные к html
function toggleActive(element, cls) {
    const elements = document.querySelectorAll(cls);
    elements.forEach(el => el.classList.remove('active'));

    element.classList.add('active');
}

function checkOnly(checkbox, cls) {
    const checkboxes = document.querySelectorAll(cls);

    checkboxes.forEach((chk) => {
        if (chk !== checkbox) {
            chk.checked = false;
        }
    });
}

function createError(message) {
    console.log(message);
    const error = document.getElementById("text-error");
    error.textContent = message;
}


// валидация и отправка
const checkX = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (-3) > value || value > 5) {
            reject("значение x некорректно");
        } else {
            resolve();
        }
    });
}

const checkY = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (-3) > value || value > 5) {
            reject("значение y некорректно");
        } else {
            resolve();
        }
    });
}

const checkR = (value) => {
    return new Promise((resolve, reject) => {
        if (isNaN(value) || (1) > value || value > 5) {
            reject("значение r некорректно");
        } else {
            resolve();
        }
    });
}

function validate(x, y, r) {
    return Promise.all([
        checkX(x),
        checkY(y),
        checkR(r)
    ]);
}

function submitForm(event) {
    event.preventDefault();
    const x = document.getElementById("x");
    const y = document.querySelector('.btn.active');
    const r = document.querySelector('.r-checkbox:checked');
    createError("");

    if (!y) {
        createError("y не определен");
    }
    else if (!r) {
        createError("r не определен");
    }
    else {
        validateAndSend(x.value, y.value, r.value);
    }
}

function validateAndSend(x, y, r) {
    validate(x, y, r)
        .then(() => {
            sendData(x, y, r);
        }).catch((error) => {
        createError(error);
    });
}

function sendData(x, y, r) {
    fetch(url + `?x=${x}&y=${y}&r=${r}`, { method: "GET" })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.statusText)
            }
            return response.text();
        })
        .then(html => {
            // if (data.message){
            //     createError(data.message);
            // }
            // else {
            //     addToTable(x, y, r, data.status, data.time, new Date().toLocaleTimeString());
            //     console.log("row added");
            //     drawDot(x, y, r, data.status);
            // }
            document.open();
            document.write(html);
            document.close();
        })
        .catch((err) => {
            createError(err)
        });
}


// отрисовка
function addToTable(x, y, r, status, time, data) {
    const tableBody = document.getElementById("records-body");
    const row = document.createElement("tr");
    row.className = "row";
    const xtd = document.createElement("td");
    xtd.className = "item";
    xtd.textContent = x;
    row.appendChild(xtd);
    const ytd = document.createElement("td");
    ytd.className = "item";
    ytd.textContent = y;
    row.appendChild(ytd);
    const rtd = document.createElement("td");
    rtd.className = "item";
    rtd.textContent = r;
    row.appendChild(rtd);
    const stattd = document.createElement("td");
    stattd.className = "item";
    stattd.textContent = status;
    row.appendChild(stattd);
    const timetd = document.createElement("td");
    timetd.className = "item";
    timetd.textContent = time;
    row.appendChild(timetd);
    const datetd = document.createElement("td");
    datetd.className = "item";
    datetd.textContent = data;
    row.appendChild(datetd);
    tableBody.prepend(row);
}

function drawDot(x, y, r, status, isLast = false) {
    const canvas = document.getElementById('canvas1') || document.getElementById('canvas2');
    const ctx = canvas.getContext('2d');
    const formula = (coord, radius) => (200 + (4 * coord * 40) / radius);
    ctx.beginPath();
    ctx.fillStyle = status ? '#52cf41' : '#EE204D';
    ctx.moveTo(200, 200);
    ctx.arc(formula(x, r), formula(-y, r), 5, 0, 2 * Math.PI);
    ctx.fill();
    ctx.closePath();
    console.log("dot has been drawn");
}

// отправка по нажатию
function byClick(event, canvas, R) {
    const r = document.querySelector('.r-checkbox:checked');

    if (!r) {
        createError("r не определен");
        return;
    }

    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    const canvasX = x - canvas.width / 2;
    const canvasY = canvas.height / 2 - y;

    const xValue = (canvasX / R * r.value).toFixed(4);
    const yValue = (canvasY / R * r.value).toFixed(4);

    console.log(`Данные нажатия: (${xValue}, ${yValue}, ${r.value})`);

    validateAndSend(xValue, yValue, r.value);
}