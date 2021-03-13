const adminInfo = document.querySelector('.forFetch2');
let output = '';

const firstNameValue = document.getElementById('firstName');
const lastNameValue = document.getElementById('lastName');

const newUser = document.querySelector('.new-user-form')
const renderPost = (post) => {
    post.forEach(user => {
        var userdata = user.roles
        output += `
                <tr class="adminInfo">
                     <td>${user.id}</td>
                     <td>${user.firstName}</td>
                     <td>${user.lastName}</td>
                     <td>${user.age}</td>
                     <td>${user.email}</td>
                     <td>${userdata[0].role.replace('ROLE_', '')}</td>
                     <td><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editModal" th:attrappend="data-target=${user.id}">Edit</button>
                     <td><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal" th:attrappend="data-target=${user.id}">Delete</button>
                     </tr>
`;
    });
    adminInfo.innerHTML = output;
}

const url = '/getUsers/';

fetch(url)
    .then(res => res.json())
    .then(user => renderPost(user))

// new user : method GET

newUser.addEventListener('submit', (e) => {
    e.preventDefault();

    console.log(firstNameValue.value)
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: firstNameValue.value,
            lastName: lastNameValue.value
        })
    })
        .then(res => res.json())
        .then(data => {
            const dataArray = [];
            dataArray.push(data);
            renderPost(dataArray);
        })
})




//getAll

//     fetch('/getUsers')
//         .then(res => res.json())
//         .then(data => console.log(data))
// }

//create new user

// fetch('/getUsers', {
//     method: 'POST',
//     headers: {
//         'Content-Type' : 'application/json'
//     },
//     body: JSON.stringify({
//         name: 'user2'
//     })
// }).then(res => {
//      return res.json()
//     })
//     .then(data => console.log(data))
//     .catch(error => console.log('ERROR'))