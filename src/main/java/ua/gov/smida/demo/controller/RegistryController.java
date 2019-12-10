package ua.gov.smida.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.gov.smida.demo.models.Dividend;
import ua.gov.smida.demo.models.DividendsHistory;
import ua.gov.smida.demo.models.PublicDividend;
import ua.gov.smida.demo.service.RegistryServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("registry")
public class RegistryController {

    private final RegistryServiceImpl registryService;

    public RegistryController(RegistryServiceImpl registryService) {
        this.registryService = registryService;
    }

    @PostMapping("/save")
    public ResponseEntity<Dividend> save(@RequestBody Dividend dividend){
        registryService.save(dividend);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/ededrpou/{edrpou}/{edrpou2}")
    public void  editEdrpou(@PathVariable("edrpou") int edrpou, @PathVariable("edrpou2") int edrpou2){
        registryService.editEdrpou(edrpou, edrpou2);
    }

    @PostMapping("/edamnt/{edrpou}/{amnt}")
    public void  editAmount(@PathVariable("edrpou") int edrpou, @PathVariable("amnt") int amnt){
        registryService.editAmount(edrpou, amnt);
    }

    @PostMapping("/edcapamnt/{edrpou}/{capamnt}")
    public void  editCapAmount(@PathVariable("edrpou") int edrpou, @PathVariable("capamnt") int capAmnt){
        registryService.editCapitalAmount(edrpou, capAmnt);
    }

    @PostMapping("/ednomval/{edrpou}/{nomval}")
    public void  editNominalValue(@PathVariable("edrpou") int edrpou, @PathVariable("nomval") double nomVal){
        registryService.editNominalValue(edrpou, nomVal);
    }

    @PostMapping("/edreldate/{edrpou}/{reldate}")
    public void  editReleaseDate(@PathVariable("edrpou") int edrpou, @PathVariable("reldate") LocalDateTime relDate){
        registryService.editReleaseDate(edrpou, relDate);
    }

    @PostMapping("/edstdp/{edrpou}/{stdp}")
    public void  editStateDutyPaid(@PathVariable("edrpou") int edrpou, @PathVariable("stdp") double stDutyPaid){
        registryService.editStateDutyPaid(edrpou, stDutyPaid);
    }

    @PostMapping("/edcomm/{edrpou}/{comment}")
    public void  editComment(@PathVariable("edrpou") int edrpou, @PathVariable("comment") String comment){
        registryService.editComment(edrpou, comment);
    }

//    @PostMapping("/saveh/{edrpou}")
//    public void  saveToHistory(@PathVariable("edrpou") int edrpou ){
//        registryService.saveToHistory(edrpou);
//    }


    @GetMapping("/pbdata/{edrpou}")
    public PublicDividend getPublicInfo(@PathVariable("edrpou") int edrpou){
        return registryService.getPublicData(edrpou);
    }

    @GetMapping("/prdata/{edrpou}")
    public List<DividendsHistory> getPrivateInfo(@PathVariable("edrpou") int edrpou){
        return registryService.getPrivateData(edrpou);
    }

//    -----------------------------------------------------------------------------------------

    @GetMapping("/amnt10page/{amount}")
    public List<PublicDividend> first10byAmount(@PathVariable("amount")int amount ){
        return registryService.first10ByAmountPb(amount);
    }


}
