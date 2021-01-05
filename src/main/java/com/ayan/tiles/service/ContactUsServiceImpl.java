package com.ayan.tiles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.tiles.entity.ContactUs;
import com.ayan.tiles.exception.EntityIdException;
import com.ayan.tiles.repository.ContactUsRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsRepository repository;

	public List<ContactUs> findAll() {
		return repository.findAll();
	}

	public ContactUs findById(Long theId) {
		Optional<ContactUs> result = repository.findById(theId);
		ContactUs theContactUs = null;

		if (result.isPresent()) {
			theContactUs = result.get();
		} else {
			throw new EntityIdException("Cannot find ContactUs: ContactUs Id " + theId + " as it doesn't exist");
		}
		return theContactUs;
	}

	public ContactUs save(ContactUs theContactUs) {
		repository.save(theContactUs);
		return theContactUs;
	}

	public void deleteById(Long theId) {
		findById(theId);
		repository.deleteById(theId);
	}

}
