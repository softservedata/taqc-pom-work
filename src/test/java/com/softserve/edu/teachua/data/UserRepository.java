package com.softserve.edu.opencart.data;

import java.text.SimpleDateFormat;
import java.util.Date;

//
// Using Singleton
//
public final class UserRepository {
    //private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private final String TIME_TEMPLATE = "HH_mm_ss_S";

	private static volatile UserRepository instance = null;

    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser defaultUser() {
        return customer();
    }
    
    public IUser admin() {
        return User.get()
				.setFirstname("firstName2")
				.setLastname("lastName2")
				.setEmail("eMail2")
				.setTelephone("telephone2")
				.setAddress1("address12")
				.setCity("city2")
				.setPostcode("postCode2")
				.setCountry("country2")
				.setRegion("regionState2")
				.setPassword("password2")
				.setSubscribe(true)
				.setFax("fax")
				.build();
    }

    public IUser customer() {
        return User.get()
        		.setFirstname("my")
				.setLastname("lastname")
				.setEmail("xdknxusqvjeovowpfk@awdrt.com")
				.setTelephone("phone")
				.setAddress1("address12")
				.setCity("city2")
				.setPostcode("postCode2")
				.setCountry("country2")
				.setRegion("regionState2")
				.setPassword("awdrt123")
				.setSubscribe(true)
				.setFax("fax")
				.build();
    }

    public IUser customerHahaha() {
        return User.get()
        		.setFirstname("hahaha")
				.setLastname("lastname")
				.setEmail("hahaha@gmail.com")
				.setTelephone("phone")
				.setAddress1("address12")
				.setCity("city2")
				.setPostcode("postCode2")
				.setCountry("Ukraine")
				.setRegion("regionState2")
				.setPassword("qwerty")
				.setSubscribe(true)
				.setFax("fax")
				.build();
    }

    public IUser newUser() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String newEmail = currentTime + "@gmail.com";
        return User.get()
				.setFirstname("firstName3")
				.setLastname("lastName3")
				.setEmail(newEmail)
				.setTelephone("telephone3")
				.setAddress1("address13")
				.setCity("city3")
				.setPostcode("postCode3")
				.setCountry("country3")
				.setRegion("regionState3")
				.setPassword(System.getenv().get("MY_PASSWORD"))
				//.setPassword("password3")
				.setSubscribe(true)
				.setFax("fax3")
				.build();
    }

    public IUser notExist() {
        return User.get()
				.setFirstname("test")
				.setLastname("lastName3")
				.setEmail("firsefilwghf")
				.setTelephone("telephone3")
				.setAddress1("address13")
				.setCity("city3")
				.setPostcode("postCode3")
				.setCountry("country3")
				.setRegion("regionState3")
				.setPassword("test")
				.setSubscribe(true)
				.setFax("fax3")
				.build();
    }
    

    //public IUser admin() {}
    //public List<IUser> fromExcel() {}
    //public List<IUser> fromDB() { create class, read, max 5-7 lines}

}


