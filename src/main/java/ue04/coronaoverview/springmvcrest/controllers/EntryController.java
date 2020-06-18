package ue04.coronaoverview.springmvcrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ue04.coronaoverview.springmvcrest.domain.Entry;
import ue04.coronaoverview.springmvcrest.services.EntryService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping(EntryController.BASE_URL)
public class EntryController {
    public static final String BASE_URL = "/api/v1/entries";
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }
    @GetMapping
    List<Entry> getAllEntries() {

       return entryService.findAllEntries();
    }

    @GetMapping("/{id}")
    public Entry getEntryByID(@PathVariable long id){
        return entryService.findEntryById(id);
    }

    @GetMapping("/district/{district}")
    public List<Entry> getEntriesByDistrict(@PathVariable String district){
        return entryService.getEntriesByDistrict(district);
    }

    @GetMapping("/date/{dateValue}")
    public List<Entry> getEntriesByDate(@PathVariable String dateValue) throws ParseException {
        DateFormat formatter;
        Calendar calNow = Calendar.getInstance();
        calNow.add(Calendar.MONTH, -1); // Für 1 Monat zurück
        Date dateBeforeAMonth = calNow.getTime();

        calNow = Calendar.getInstance();
        calNow.add(Calendar.DATE, -7); //Für eine Woche zurück
        Date dateBeforeAWeek = calNow.getTime();

        calNow = Calendar.getInstance();
        calNow.add(Calendar.DATE, -1); //Für einen Tag zurück
        Date dateBeforeADay = calNow.getTime();

        calNow = Calendar.getInstance();
        calNow.add(Calendar.HOUR, -1); //Für eine Stunde zurück
        Date dateBeforeAHour = calNow.getTime();
        Date filterTime = new Date();

        //Date now = new Date();
        //long filtertime=0;
        //formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //d = formatter.parse(datevalue);
        if (dateValue.equals("stunde")) filterTime = dateBeforeAHour;//now.getTime()-3600000; // letzte Stunde
        else if(dateValue.equals("tag")) filterTime = dateBeforeADay;//now.getTime()-3600000*24; // letzte 24h
        else if(dateValue.equals("woche")) filterTime = dateBeforeAWeek;//now.getTime()-3600000*24*7; // letzte Woche
        else if(dateValue.equals("monat")) filterTime = dateBeforeAMonth;//now.getTime()-3600000*24*7*30; // letztes Monat
        return entryService.getEntriesAfterDate(filterTime);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry saveEntry(@RequestBody Entry entry){

        return entryService.saveEntry(entry);
    }

    @PutMapping("/{id}")
    public Entry replaceEntry(@PathVariable Long id, @RequestBody Entry newEntry) {

        return entryService.replaceEntry(id, newEntry);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable long id){
        Entry entry = entryService.deleteById(id);

        if (entry != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping(value = {"/district/{district}/date/{datevalue}", "/district//date/{datevalue}","/district/{district}/date/","/district//date/"})
    public List<Entry>  getFilteredEntries(@PathVariable(value="district",required = false) String district, @PathVariable(value="datevalue",required = false) String datevalue){
        List returnList=entryService.findAllEntries();
        List dateList = new ArrayList<Entry>();
        List districtList = new ArrayList<Entry>();

        if(datevalue!=null&&!datevalue.equals("")) {
            DateFormat formatter;
            Calendar calNow = Calendar.getInstance();
            calNow.add(Calendar.MONTH, -1); // Für 1 Monat zurück
            Date dateBeforeAMonth = calNow.getTime();

            calNow = Calendar.getInstance();
            calNow.add(Calendar.DATE, -7); //Für eine Woche zurück
            Date dateBeforeAWeek = calNow.getTime();

            calNow = Calendar.getInstance();
            calNow.add(Calendar.DATE, -1); //Für einen Tag zurück
            Date dateBeforeADay = calNow.getTime();

            calNow = Calendar.getInstance();
            calNow.add(Calendar.HOUR, -1); //Für eine Stunde zurück
            Date dateBeforeAHour = calNow.getTime();
            Date filtertime = new Date();

            //Date now = new Date();
            //long filtertime=0;
            //formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //d = formatter.parse(datevalue);
            if (datevalue.equals("stunde")) filtertime = dateBeforeAHour;//now.getTime()-3600000; // letzte Stunde
            else if (datevalue.equals("tag")) filtertime = dateBeforeADay;//now.getTime()-3600000*24; // letzte 24h
            else if (datevalue.equals("woche"))
                filtertime = dateBeforeAWeek;//now.getTime()-3600000*24*7; // letzte Woche
            else if (datevalue.equals("monat"))
                filtertime = dateBeforeAMonth;//now.getTime()-3600000*24*7*30; // letztes Monat
            returnList=entryService.getEntriesAfterDate(filtertime);


        }

        if(district!=null&&!district.equals("")){
            returnList= entryService.filterByDistrict(returnList,district);
        }

        return returnList;


    }

}
