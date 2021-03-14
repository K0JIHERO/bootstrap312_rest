document.getElementById('saved').addEventListener('click', createUser);
var tableBody = "";
var indexDeleted = [];
indexDeleted.push(0);

async function createUser() {
    console.log("wft?creation")
    const create = await fetch("/getUsers", {
        method: 'POST',
        body: JSON.stringify({
            firstName: createfirstName.value,
            lastName: createlastName.value,
            age: createage.value,
            email: createemail.value,
            password: createpassword.value,
            role: createRoles.value,
        }),
        headers: {
            "Contetnt-Type": "Application/json; charset=UTF-8"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json));
}
