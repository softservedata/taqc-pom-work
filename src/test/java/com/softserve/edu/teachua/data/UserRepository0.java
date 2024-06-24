package com.softserve.edu.opencart.data;

//
// TODO
// Use Repository
// Use Singleton
//
public final class UserRepository0 {
	
    public User0 getExistUser() {
        return new User0("firstname", "lastname",
                "email234@awdrt.com", "+380671234567", "fax",
                "company", "address1", "address2",
                "city", "postcode", "Ukraine",
                "Kyiv", "qwerty", true);
    }

    public User0 getNewUser() {
        //
        // TODO
        //
        return new User0();
    }
    
    public User0 getInvalidUser() {
        //
        // TODO
        //
        return new User0();
    }
    
}
