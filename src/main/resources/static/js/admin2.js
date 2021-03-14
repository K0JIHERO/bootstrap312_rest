document.getElementById('nav-home-tab').addEventListener(getData())

async function getData() {
    const users = await fetch('/getUsers');
    const response = await users.json();
    console.log(response);

    response.forEach(user => {
        var userdata = user.roles
        if(userdata[0] != null) {
            var role = userdata[0].role.replace('ROLE_', '')
        } else role = '-';

        infData.innerHTML += `
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
            <td>${role}</td>
            <td><button onclick="updateUser(${user.id})" type="submit" id="loadInfo" class="button button-with-mouse-on btn btn-info btn-sm">EDIT</button> </td>
            <td><button onclick="deleteUser(${user.id})" type="submit" id="loadInfo" class="button button-with-mouse-on btn btn-danger btn-sm">DELETE</button> </td>
        </tr>`
    })
}

//cearte
function onFormSubmit(){
    var formData = readFormData();

}
function readFormData() {
    var formData = {};
    formData['firstName'] = document.getElementById('createfirstName').value;
    formData['lastName'] = document.getElementById('createlastName').value;
    formData['age'] = document.getElementById('createage').value;
    formData['email'] = document.getElementById('createemail').value;
    formData['password'] = document.getElementById('createpassword').value;
    return formData;
}


//delete
async function deleteUser(userId) {
    console.log(userId);
    var url = "/getUsers/" + userId;
    console.log(url);
    const user = await fetch(url, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(data => console.log(data));

//    show deleted info
    const users = await fetch('/getUsers');
    const resp = await users.json();
    console.log(resp);
    infData.innerHTML = "";
    resp.forEach(user => {
        var userdata = user.roles
        if(userdata[0] != null) {
            var role = userdata[0].role.replace('ROLE_', '')
        } else role = '-';

        if (user.id = userId) {
            infData.innerHTML += `
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
            <td>${role}</td>
            <td><button onclick="updateUser(${user.id})" type="submit" id="loadInfo" class="button button-with-mouse-on btn btn-info btn-sm">EDIT</button> </td>
            <td><button onclick="deleteUser(${user.id})" type="submit" id="loadInfo" class="button button-with-mouse-on btn btn-danger btn-sm">DELETE</button> </td>
        </tr>`
        }
    });
}
