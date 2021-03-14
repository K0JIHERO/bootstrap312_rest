const adminInfo = document.querySelector('.forFetch2');
let output = '';

const firstNameValue = document.getElementById('firstName');
const lastNameValue = document.getElementById('lastName');
const ageValue = document.getElementById('age');
const emailValue = document.getElementById('email');
const passwordValue = document.getElementById('password');
const roleValue = document.getElementById('selectedRoles');

const idea = document.getElementById('idea')
const jname = document.getElementById('jname')
const jlast = document.getElementById('jlast')
const jemail = document.getElementById('jemail')
const jage = document.getElementById('jage')
const jrole = document.getElementById('jrole')

const newUser = document.querySelector('.new-user-form')

const renderPost = (post) => {
    post.forEach(user => {
        var userdata = user.roles
        console.log(userdata)
        if(userdata[0] != null) {
            var role = userdata[0].role.replace('ROLE_', '')
        } else role = '-';
            idea.appendChild(document.createTextNode(user.id))
            jname.appendChild(document.createTextNode(user.firstName))
            jlast.appendChild(document.createTextNode(user.lastName))
            jage.appendChild(document.createTextNode(user.age))
            jemail.appendChild(document.createTextNode(user.email))
            jrole.appendChild(document.createTextNode(role))



//         output += `
// <!--                 <tr class="adminInfo">-->
//
//                      <td>${user.firstName}</td>
//                      <td>${user.lastName}</td>
//                      <td>${user.age}</td>
//                      <td>${user.email}</td>
//                      <td>${role}</td>
// `;
//     });
//     adminInfo.innerHTML = output;
})}

const url = '/getUsers/';
const url2 = '/getUsers/29';

fetch(url)
    .then(res => res.json())
    .then(user => renderPost(user))



// detele
fetch(url2).then( res => console.log(res))


// new user : method GET

newUser.addEventListener('submit', (e) => {
    e.preventDefault();

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: firstNameValue.value,
            lastName: lastNameValue.value,
            age: ageValue.value,
            email: emailValue.value,
            password: passwordValue.value,
            // role: roleValue.value
        })
    })
        .then(res => res.json()).then((data) => console.log(data))
        //     return res.json()
        // })
        // .then(data => console.log(data))
        // .catch(error => console.log('ERROR'))
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