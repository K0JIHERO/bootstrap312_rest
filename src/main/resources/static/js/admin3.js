src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
crossOrigin="anonymous"

//getall

document.getElementById('nav-home-tab').addEventListener('load', getData())

function getData(){
fetch('/getUsers')
.then((res) => res.json())
.then((data) => {
    data.forEach(user => {
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
            <td><button type="submit" id="myBtn" class="btn btn-info btn-sm">EDIT</button> </td>
            <td><button onclick="deleteUser(${user.id})" type="submit" id="loadInfo" class="button button-with-mouse-on btn btn-danger btn-sm">DELETE</button> </td>
        </tr>`
    })
})}


// DELETE

$(document).ready(function(){
$("#myBtn").click(function(){
    $("#editModal").modal();
});
});

//delete
function deleteUser(userId) {
    console.log(userId);
    var url = "/getUsers/" + userId;
    console.log(url);
    const user = fetch(url, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(data => console.log(data))
}



//create

// fetch('/createUser', {
//     method: 'POST',
//     body: JSON.stringify({
//         firstName: 'b',
//         lastName: 'c'
//     }),
//     headers: {
//         "Content-Type": "application/json; charset=UTF-8"
//     }
// })
//     .then(res => res.json())
//     .then(data => console.log(data));

