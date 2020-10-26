package com.vinayakborhade.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vinayakborhade.messenger.database.DatabaseClass;
import com.vinayakborhade.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("vinayak", new Profile(1L, "vinayak", "vinayak", "vin") );
	}
	
	public List<Profile> getAllProfiles() {
		System.out.println("get all profiles");
		List<Profile> list = new ArrayList<Profile>(profiles.values());
		
		System.out.println("Profiles - ");
		for(Profile p: list) {
			System.out.println(p.toString());
		}
		return list;
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		System.out.println("inside addProfile()");
		profile.setId(profiles.size() + 1);
		try {
			System.out.println("Profile to be inserted, " + profile.toString());
			profiles.put(profile.getProfileName(), profile);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
		
}
