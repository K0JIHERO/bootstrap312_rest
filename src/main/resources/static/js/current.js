const uri = "/getUsers";
let users = [];

// display all

function getUsers() {
    fetch(uri)
        .then(response => response.json())
        .then(data => _displayUsers(data))
        .catch(error => console.log("Unable to get users.", error));
}

function _displayUsers(data) {
    const tBody = document.getElementById("users")
    tBody.innerHTML = "";

    data.forEach(user => {
        var userdata = user.roles
        if(userdata[0] != null) {
            var role = userdata[0].role.replace('ROLE_', '')
        } else role = '-';

        let editButton = document.createElement("a");
        editButton.href = "#editUserModal";
        editButton.className = "edit";
        editButton.setAttribute("onclick", `displayEditForm(${user.id})`);
        editButton.setAttribute("data-toggle", "modal");
        editButton.innerHTML =
            "<button data-toggle='tooltip' title='Edit' class='btn btn-info btn-sm'>EDIT</button>";
        
        let deleteButton = document.createElement("a");
        deleteButton.href = "#deleteUserModal";
        deleteButton.className = "delete";
        deleteButton.setAttribute("onclick", `displayDeleteForm(${user.id})`);
        deleteButton.setAttribute("data-toggle", "modal");
        deleteButton.innerHTML =
            "<button data-toggle='tooltip' title='Delete' class='btn btn-danger btn-sm'>DELETE</button>";
        
        let tr = tBody.insertRow();

        let td1 = tr.insertCell(0);
        let userId = document.createTextNode(user.id);
        td1.appendChild(userId);

        let td2 = tr.insertCell(1);
        let firstName = document.createTextNode(user.firstName);
        td2.appendChild(firstName);

        let td3 = tr.insertCell(2);
        let lastName = document.createTextNode(user.lastName);
        td3.appendChild(lastName);

        let td4 = tr.insertCell(3);
        let email = document.createTextNode(user.email);
        td4.appendChild(email);

        let td5 = tr.insertCell(4);
        let age = document.createTextNode(user.age);
        td5.appendChild(age);

        let td6 = tr.insertCell(5);
        let roles = document.createTextNode(role);
        td6.appendChild(roles);

        let td7 = tr.insertCell(6);
        td7.appendChild(editButton);

        let td8 = tr.insertCell(7);
        td8.appendChild(deleteButton);
    });

    users = data;
}

// delete user

function deleteUser() {
    const userId = document.getElementById("delete-id").value.trim();
    fetch(`${uri}/${userId}`, {
        method: "DELETE"
    })
        .then(() => getUsers())
        .catch(error => console.error("Unable to delete User.", error));
}

function displayDeleteForm(id) {
    const user = users.find(user => user.id === id);
    document.getElementById("delete-id").value = user.id;
    document.getElementById("delete-firstName").value = user.firstName;
    document.getElementById("delete-lastName").value = user.lastName;
    document.getElementById("delete-email").value = user.email;
    document.getElementById("delete-age").value = user.age;

    var userdata = user.roles
    if(userdata[0] != null) {
        var role = userdata[0].role.replace('ROLE_', '')
    } else role = '-';

    document.getElementById("delete-role").value = role;
}

//////////////////////// update user/////////////////////

function displayEditForm(id) {
    const user = users.find(user => user.id === id);
    document.getElementById("edit-id").value = user.id;
    document.getElementById("edit-firstName").value = user.firstName;
    document.getElementById("edit-lastName").value = user.lastName;
    document.getElementById("edit-email").value = user.email;
    document.getElementById("edit-age").value = user.age;
    document.getElementById("edit-password").value = user.password;
    document.getElementById("edit-role").value = user.role;

}

function updateUser() {
    const userId = document.getElementById("edit-id").value.trim();
    let roleList = []
    roleList.push(document.getElementById("edit-role").value.trim())
    console.log(document.getElementById("edit-role").value)

    const user = {
        id: parseInt(userId),
        firstName: document.getElementById("edit-firstName").value.trim(),
        lastName: document.getElementById("edit-lastName").value.trim(),
        email: document.getElementById("edit-email").value.trim(),
        age: parseInt(document.getElementById("edit-age").value.trim()),
        password: document.getElementById("edit-password").value.trim(),
        roles: roleList
    };

    fetch(`${uri}/${userId}`, {
        method: "PUT",
        headers: {
            Accept: "application/json", "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
        .then(() => getUsers())
        .catch(error => console.error("Unable to update user.", error));

    return false;    
}

//  new user

function addUser() {
    const firstNameInput = document.getElementById("add-firstName");
    const lastNameInput = document.getElementById("add-lastName");
    const emailInput = document.getElementById("add-email");
    const ageInput = document.getElementById("add-age");
    const passwordInput = document.getElementById("add-password");
    const roleInput = document.getElementById("add-role")

    let roleList = [];
    roleList.push(roleInput.value.trim())

    const user = {
        firstName: firstNameInput.value.trim(),
        lastName: lastNameInput.value.trim(),
        email: emailInput.value.trim(),
        age: parseInt(ageInput.value.trim()),
        password: passwordInput.value.trim(),
        roles: roleList
    };
    console.log(JSON.stringify(user));
    fetch(uri, {
        method: "POST", 
        headers: {
            Accept: "application/json", "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(() => {
            getUsers();
            firstNameInput.value = "";
            lastNameInput.value = "";
            emailInput.value = "";
            ageInput.value = "";
            passwordInput.value = "";
            roleInput.value = "";
        })
        .catch(error => console.error("Unable to add User", error));
}



