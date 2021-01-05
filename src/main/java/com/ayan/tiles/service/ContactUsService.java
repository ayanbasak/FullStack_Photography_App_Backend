package com.ayan.tiles.service;

import java.util.List;

import com.ayan.tiles.entity.ContactUs;

public interface ContactUsService {

	public List<ContactUs> findAll();

	public ContactUs findById(Long theId);

	public ContactUs save(ContactUs theContactUs);

	public void deleteById(Long theId);

}
