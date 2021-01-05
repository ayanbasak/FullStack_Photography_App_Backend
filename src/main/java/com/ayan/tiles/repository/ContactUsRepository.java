package com.ayan.tiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayan.tiles.entity.ContactUs;


@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {


}