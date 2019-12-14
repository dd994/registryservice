package ua.gov.smida.demo.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.gov.smida.demo.models.Dividend;
import ua.gov.smida.demo.models.DividendsHistory;
import ua.gov.smida.demo.service.RegistryServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("registry")
public class RegistryController {

    private final RegistryServiceImpl registryService;

    public RegistryController(RegistryServiceImpl registryService) {
        this.registryService = registryService;
    }

    //    -------------------------------------  basic  -------------------------------------------------

    @PostMapping("/save")
    public ResponseEntity<Dividend> save(@RequestBody Dividend dividend) {
        registryService.save(dividend);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/getdiv/{edrpou}")
    public Dividend getDividend(@PathVariable("edrpou") int edrpou){
        return  registryService.getDividend(edrpou);
    }

    @GetMapping("/allpublic")
    public List<Dividend> getAllPublicData(){
        return  registryService.getAllPublicData();
    }

    @GetMapping("/prdata/{edrpou}")
    public List<DividendsHistory> getPrivateData(@PathVariable("edrpou")int edrpou){
        return registryService.getPrivateData(edrpou);
    }

    //    -------------------------------------  edit  -------------------------------------------------

    @PostMapping("/ededrpou/{edrpou}/{edrpou2}")
    public void editEdrpou(@PathVariable("edrpou") int edrpou, @PathVariable("edrpou2") int edrpou2) {
        registryService.editEdrpou(edrpou, edrpou2);
    }

    @PostMapping("/edamnt/{edrpou}/{amnt}")
    public void editAmount(@PathVariable("edrpou") int edrpou, @PathVariable("amnt") int amnt) {
        registryService.editAmount(edrpou, amnt);
    }

    @PostMapping("/edcapamnt/{edrpou}/{capamnt}")
    public void editCapAmount(@PathVariable("edrpou") int edrpou, @PathVariable("capamnt") int capAmnt) {
        registryService.editCapitalAmount(edrpou, capAmnt);
    }

    @PostMapping("/ednomval/{edrpou}/{nomval}")
    public void editNominalValue(@PathVariable("edrpou") int edrpou, @PathVariable("nomval") double nomVal) {
        registryService.editNominalValue(edrpou, nomVal);
    }

    @PostMapping("/edreldate/{edrpou}/{reldate}")
    public void editReleaseDate(@PathVariable("edrpou") int edrpou, @PathVariable("reldate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate relDate) {
        registryService.editReleaseDate(edrpou, relDate);
    }

    @PostMapping("/edstdp/{edrpou}/{stdp}")
    public void editStateDutyPaid(@PathVariable("edrpou") int edrpou, @PathVariable("stdp") double stDutyPaid) {
        registryService.editStateDutyPaid(edrpou, stDutyPaid);
    }

    @PostMapping("/edcomm/{edrpou}/{comment}")
    public void editComment(@PathVariable("edrpou") int edrpou, @PathVariable("comment") String comment) {
        registryService.editComment(edrpou, comment);
    }

//    -------------------------------------  filters -------------------------------------------------

    @GetMapping(value = {"/filter/amntg/{amnt}/{pr}", "/filter/amntg/{amnt}"})
    public List byGrAmountFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("amnt") int amnt) {
        return registryService.findAllByAmountGreaterThanEqual(amnt, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/amntl/{amnt}/{pr}", "/filter/amntl/{amnt}"})
    public List byLessAmountFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("amnt") int amnt) {
        return registryService.findAllByAmountLessThanEqual(amnt, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/gnv/{nv}/{pr}", "/filter/gnv/{nv}"})
    public List byGrNomValFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("nv") double nv) {
        return registryService.findAllByNominalValueGreaterThanEqual(nv, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/lnv/{nv}/{pr}", "/filter/lnv/{nv}"})
    public List byLessNomValFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("nv") double nv) {
        return registryService.findAllByNominalValueLessThanEqual(nv, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/gtnv/{tnv}/{pr}", "/filter/gtnv/{tnv}"})
    public List byGrTotNomValFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("tnv") double tnv) {
        return registryService.findAllByTotalNominalValueGreaterThanEqual(tnv, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/ltnv/{tnv}/{pr}", "/filter/ltnv/{tnv}"})
    public List byLessTotNomValFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("tnv") double tnv) {
        return registryService.findAllByTotalNominalValueLessThanEqual(tnv, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/arl/{rdate}/{pr}", "/filter/arl/{rdate}"})
    public List byAfterReleaseDateFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("rdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rdate) {
        return registryService.findAllByReleaseDateIsAfter(rdate, controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/filter/brl/{rdate}/{pr}", "/filter/brl/{rdate}"})
    public List byBeforeReleaseDateFilter(@PathVariable(value = "pr", required = false) String str, @PathVariable("rdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rdate) {
        return registryService.findAllByReleaseDateIsBefore(rdate, controllerAccessDataComparator(str));
    }

    @GetMapping("/filter/gca/{ca}")
    public List byGrCapitalAmountFilter(@PathVariable("ca") int capAmount) {
        return registryService.findAllByCapitalAmountGreaterThanEqual(capAmount);
    }

    @GetMapping("/filter/lca/{ca}")
    public List byLessCapitalAmountFilter(@PathVariable("ca") int capAmount) {
        return registryService.findAllByCapitalAmountLessThanEqual(capAmount);
    }

    @GetMapping("/filter/gsdp/{sdp}")
    public List byGrStateDutyPaidFilter(@PathVariable("sdp") double sdp) {
        return registryService.findAllByStateDutyPaidGreaterThanEqual(sdp);
    }

    @GetMapping("/filter/lsdp/{sdp}")
    public List byLessStateDutyPaidFilter(@PathVariable("sdp") double sdp) {
        return registryService.findAllByStateDutyPaidLessThanEqual(sdp);
    }

//    ------------------------------------   sorting   ----------------------------------------

    @GetMapping("/sort/bye")
    public List sortByEdrpouFromHistoryToMaxValue() {
        return registryService.sortByEdrpouFromHistoryToMaxValue();
    }

    @GetMapping(value = {"/sort/amntomax", "/sort/amntomax/{pr}"})
    public List sortByAmountFromMinToMax(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByAmountFromMinToMax(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/amntomin", "/sort/amntomin/{pr}"})
    public List sortByAmountFromMaxToMin(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByAmountFromMaxToMin(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/nvtomax", "/sort/nvtomax/{pr}"})
    public List sortByNominalValueFromMinToMax(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByNominalValueFromMinToMax(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/nvtomin", "/sort/nvtomin/{pr}"})
    public List sortByNominalValueFromMaxToMin(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByNominalValueFromMaxToMin(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/tnvtomax", "/sort/tnvtomax/{pr}"})
    public List sortByTotalNominalValueFromMinToMax(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByTotalNominalValueFromMinToMax(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/tnvtomin", "/sort/tnvtomin/{pr}"})
    public List sortByTotalNominalValueFromMaxToMin(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByTotalNominalValueFromMaxToMin(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/tolatest", "/sort/tolatest/{pr}"})
    public List sortByReleaseDateFromLatestToOld(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByReleaseDateFromOldToLatest(controllerAccessDataComparator(str));
    }

    @GetMapping(value = {"/sort/toold", "/sort/toold/{pr}"})
    public List sortByReleaseDateFromOldToLatest(@PathVariable(value = "pr", required = false) String str) {
        return registryService.sortByReleaseDateFromLatestToOld(controllerAccessDataComparator(str));
    }

//    ----------------------   private technical method to simplify the code   ------------------------

    private boolean controllerAccessDataComparator(String str) {
        return str != null && str.equalsIgnoreCase("pr");
    }

}
