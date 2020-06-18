package ue04.coronaoverview.springmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ue04.coronaoverview.springmvcrest.domain.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
