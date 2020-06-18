package ue04.coronaoverview.springmvcrest.services;

import ue04.coronaoverview.springmvcrest.domain.Entry;

import java.util.Date;
import java.util.List;

public interface EntryService {

    Entry findEntryById(Long id);
    List<Entry> findAllEntries();

    Entry saveEntry(Entry entry);

    Entry deleteById(Long id);

    Entry replaceEntry(Long id,Entry entry);

    List<Entry> getEntriesByDistrict(String district);

    List<Entry> getEntriesAfterDate(Date date);

    List<Entry> filterByDistrict(List<Entry> list,String district);
}
