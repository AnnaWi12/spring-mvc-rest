package ue04.coronaoverview.springmvcrest.services;

import org.springframework.stereotype.Service;
import ue04.coronaoverview.springmvcrest.domain.Entry;
import ue04.coronaoverview.springmvcrest.repositories.EntryRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry findEntryById(Long id) {
        return entryRepository.findById(id).get();
    }

    @Override
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public Entry deleteById(Long id){
        Entry entry = findEntryById(id);

        if (entry == null) return null;

        else {
            entryRepository.deleteById(id);
            return entry;
        }
    }

    @Override
    public Entry replaceEntry(Long id,Entry newEntry) {
        return entryRepository.findById(id)
                .map(employee -> {
                    employee.setDistrict(newEntry.getDistrict());
                    employee.setStatus(newEntry.getStatus());
                    return entryRepository.save(employee);
                })
                .orElseGet(() -> {
                    return entryRepository.save(newEntry);
                });
    }

    @Override
    public List<Entry> getEntriesByDistrict(String district) {

        return entryRepository.findAll().stream().filter(entry -> entry.getDistrict().equals(district)).collect(Collectors.toList());
    }

    @Override
    public List<Entry> getEntriesAfterDate(Date date) {
        return entryRepository.findAll().stream().filter(entry -> entry.getDate().after(date)).collect(Collectors.toList());
    }

    @Override
    public List<Entry> filterByDistrict(List<Entry> list,String district) {

        return list.stream().filter(entry -> entry.getDistrict().equals(district)).collect(Collectors.toList());
    }
}
