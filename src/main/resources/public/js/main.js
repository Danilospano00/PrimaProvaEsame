
window.onload = function() {
    var picker = new Pikaday({
        field: document.getElementById('dataDiScadenza'),
        firstDay: 1,
        minDate: new Date(),
        maxDate: new Date(2025, 12, 31),
        yearRange: [2000, 2025],
        format: 'D-M-YYYY',
        toString(date, format) {
            // you should do formatting based on the passed format,
            // but we will just return 'D/M/YYYY' for simplicity
            const day = date.getDate();
            const daylong = date.toLocaleString('default', { // use localestring weekday to get the long day
                weekday: 'long'
            });
            const month = ("00" + (date.getMonth() + 1)).slice(-2);
            const monthlong = date.toLocaleString('default', {
                month: 'long' // use localestring month to get the long month
            });
            const year = date.getFullYear();
            return `${day}/${month}/${year}`; // just format as you wish
        }
    });
    if (document.getElementById("titoloPaginaReminders")!=undefined)
        loadReminders();
};


function traduzione() {
    const url = window.location.origin;
    let testoAreaEl = document.getElementById("testo");
    if (testoAreaEl.value!=="") {
        let payload = { "langFrom": "it", "langTo": "en", "text": testoAreaEl.value };
        request('POST', `${url}/api/translate/`, payload).then((risp) => {
            let json = JSON.parse(risp);
            testoAreaEl.value = json.translatedText;
        }).catch((err) => {
            console.log(err);
        });
    }
}

function request(type, url, payload) {
    return new Promise((resolve, reject) => {
        httpRequest(type, url, resolve, reject, payload);
    });
}

function loadReminders() {

    const url = window.location.origin;
    let type = getParameterActualPage("type");
    let dt = document.getElementById("dataDiScadenza").value;
    let gg = pad(dt.split("/")[0], 2);
    let mm = pad(dt.split("/")[1], 2);
    let yyyy = dt.split("/")[2];
    let dtfrom = new Date(yyyy, Number(mm)-1, gg, 11, 0, 0, 0);
    dtfrom = dtfrom.toISOString().slice(0,10);

    request('GET', `${url}/api/posts/?type=${type}&fromDate=${dtfrom}`, null).then((risp) => {
        populateTableData(risp);
    }).catch((err) => {
        console.log(err);
    });
}


function populateTableData(data) {
    let json = JSON.parse(data);
    let tbBody= document.getElementById("dyn_body");
    let rowCount = tbBody.rows.length;
    for (let i = rowCount - 1; i >= 0; i--) {
        tbBody.deleteRow(i);
    }
    rowCount = tbBody.rows.length;

    for (let j = 0; j < json.length; j++) {

        let row = tbBody.insertRow(rowCount);

        let thElements = document.getElementById("dyn_head").firstElementChild.children;

        let i = 0;
        for(let item in thElements) {
            let fieldName = thElements[item].id;
            if (fieldName!=undefined && fieldName!="" && !fieldName.startsWith("an_") && thElements[item].tagName=="TH") {

                let cell = row.insertCell(i++);
                let fieldNameArr = fieldName.split(".");
                if (fieldNameArr.length>1)
                    cell.innerHTML = json[j][fieldNameArr[0]][fieldNameArr[1]];
                else {
                    cell.innerHTML = json[j][fieldNameArr[0]];
                    if (fieldNameArr[0]=="dataDiScadenza") {
                        let date = new Date(cell.innerHTML);
                        cell.innerHTML = date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
                    }
                    if (fieldNameArr[0]=="attivo") {
                        cell.innerHTML = (fieldName[0]?"attivo":"Non attivo");
                    }
                }
            }
            else {
                // <a href="/follow/admin@admin.it/">Segui Autore</a>
                if (fieldName === "an_follow") {
                    let cell = row.insertCell(i++);
                    let a = document.createElement("a");
                    a.href = "/follow/".concat(json[j].autore.email).concat("/");
                    a.innerHTML= "Segui Autore";
                    cell.append(a);
                }
                // <a href="/reminders/admin@admin.it/">Tutti i Post di admin admin</a>
                if (fieldName === "an_AllAuthPosts") {
                    let cell = row.insertCell(i++);
                    let a = document.createElement("a");
                    a.href = "/reminders/".concat(json[j].autore.email).concat("/");
                    a.innerHTML= "Segui tutti i Post di ".concat(json[j].autore.lastName).concat(" ").concat(json[j].autore.firstName);
                    cell.append(a);
                }
                /*
                    <form method="get" action="/reminder/329c7a62-ab35-456c-b817-a2af14ac48c1/">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="submit" value="elimina">
                    </form>
                */
                if (fieldName === "an_AllCurrentUsersPosts" && json[j].droppable==true) {
                    let cell = row.insertCell(i++);
                    let td = document.createElement("td");
                    let form = document.createElement("form");
                    form.method="get";
                    form.action="/reminder/".concat(json[j].id).concat("/");
                    let inputHidden = document.createElement("input");
                    inputHidden.type="hidden";
                    inputHidden.name="_method";
                    inputHidden.value="DELETE";
                    let inputSubmit = document.createElement("input");
                    inputSubmit.type = "submit";
                    inputSubmit.value = "elimina";
                    form.append(inputHidden);
                    form.append(inputSubmit);
                    cell.append(form);
                }
            }
        }
    }
}


function httpRequest(type, url, resolve, reject, body) {
    const xhr = new XMLHttpRequest();
    xhr.open(type, url, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.setRequestHeader("Authorization", "Bearer ".concat(document.getElementById("jwtToken").value));
    if (body!=null)
        xhr.send(JSON.stringify(body));
    else
        xhr.send();
    xhr.onload = function() {
        if(this.status === 200) {
            resolve(this.responseText);
        } else {
            reject("Errore!");
        }
    }
}


function getParameterActualPage(parameterName) {
    let url = new URL(window.location.href);
    return url.searchParams.get(parameterName);
}

function pad(str, max) {
    str = str.toString();
    return str.length < max ? pad("0" + str, max) : str;
}