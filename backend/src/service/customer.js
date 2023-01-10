const repoCustomer = require('../repository/customer')

const getAll = async () => {
  console.log("Getting all customers...")
  const customers = await repoCustomer.getAll()
  console.log(customers)
  return customers
}

const updateCustomerById = async (id, {...customer}) => {
  await repoCustomer.updateCustomerById(id, serializeUser(customer));
  const cus =  await repoCustomer.getCustomerById(id)
  console.log("returning: ");
  console.log(outputUser(cus[0]));
  return outputUser(cus[0])
}

const getCustomerById = async (id) => {
  const customer = await repoCustomer.getCustomerById(id)
  return outputUser(customer[0])
}

//registerCustomer = async ({...customer})
const registerCustomer = async ({firstname_c, lastname_c, email_c, password_c, phonenumber_c, bedrijf_opleiding_c,
  contactpersoon1_phone_c ,contactpersoon1_email_c ,contactpersoon1_firstname_c ,contactpersoon1_lastname_c ,contactpersoon2_phone_c ,
contactpersoon2_email_c ,contactpersoon2_firstname_c ,contactpersoon2_lastname_c}) => {
    console.log('register customer with: ')
    console.log(firstname_c, lastname_c, email_c, password_c, phonenumber_c, bedrijf_opleiding_c, 
      contactpersoon1_phone_c ,contactpersoon1_email_c ,contactpersoon1_firstname_c ,contactpersoon1_lastname_c ,contactpersoon2_phone_c ,
    contactpersoon2_email_c ,contactpersoon2_firstname_c ,contactpersoon2_lastname_c)

  const id = await repoCustomer.registerCustomer(firstname_c, lastname_c, email_c, password_c, phonenumber_c, bedrijf_opleiding_c,
  contactpersoon1_phone_c ,contactpersoon1_email_c ,contactpersoon1_firstname_c ,contactpersoon1_lastname_c ,contactpersoon2_phone_c ,
  contactpersoon2_email_c ,contactpersoon2_firstname_c ,contactpersoon2_lastname_c)

  customer = await repoCustomer.getCustomerById(id)
  return outputUser(customer[0])
}

const loginCustomer = async ({email,password}) => {
  const customer = await repoCustomer.loginCustomer(email, password)
  if(customer[0]){
    return outputUser(customer[0]);
  }
  return null;
}

function outputUser(user){
  let cp1 = null
  let cp2 = null

  if(user.contact1_email){
    cp1 = {
      contact1_phone: user.contact1_phone,
      contact1_email: user.contact1_email,
      contact1_firstname: user.contact1_firstname,
      contact1_lastname: user.contact1_lastname
    }
  }
  if(user.contact2_email){
    cp2 = {
      contact2_phone: user.contact2_phone,
      contact2_email: user.contact2_email,
      contact2_firstname: user.contact2_firstname,
      contact2_lastname: user.contact2_lastname
    }
  }
  return {
    id: user.id,
    firstname: user.firstname,
    lastname: user.lastname,
    email: user.email,
    phonenumber: user.phonenumber,
    bedrijf_opleiding: user.bedrijf_opleiding,
    contactPs1: cp1,
    contactPs2: cp2
  
  }
}

  function serializeUser(user){
    let cp1 = user.contactPs1;
    let cp2 = user.contactPs2;
  
    if(!user.contactPs1 || !user.contactPs1.contact1_email){
      cp1 = {
        contact1_phone: null,
        contact1_email: null,
        contact1_firstname:null,
        contact1_lastname: null
      }
    }
    if(!user.contactPs2 || !user.contactPs2.contact2_email){
      cp2 = {
        contact2_phone: null,
        contact2_email: null,
        contact2_firstname: null,
        contact2_lastname: null
      }
  }

  return {
    id: user.id,
    firstname: user.firstname,
    lastname: user.lastname,
    email: user.email,
    phonenumber: user.phonenumber,
    bedrijf_opleiding: user.bedrijf_opleiding,
    contactPs1: cp1,
    contactPs2: cp2
  
  }
}





module.exports = {
  getAll,
  getCustomerById,
  updateCustomerById,
  registerCustomer,
  loginCustomer
}