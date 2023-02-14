//package com.wms.inwms.controller;
//
//
//import com.wms.inwms.domain.cj.CJDto;
//import com.wms.inwms.domain.cj.CjAddress;
//
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//
//import org.json.simple.JSONObject;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class TestController implements ErrorController {
//
//    //@Autowired
//    //private ClientRegistrationRepository clientRegistrationRepository;
//
//    @GetMapping("/test")
//    public String testController() {
//        return "Test";
//    }
//
//    @GetMapping("/proxy")
//    public String proxy() {
//        System.out.println("test");
//        return "/proxy";
//    }
//
//    @PostMapping("/xlsx")
//    public List<CJDto> excel(@RequestParam MultipartFile file) throws Exception {
//        List<CJDto> cjDtoList = new ArrayList<>();
//        try {
//            if(file.getOriginalFilename().contains(".xls")) {
//                HSSFWorkbook hss = new HSSFWorkbook(file.getInputStream());
//                HSSFSheet hsSheet = hss.getSheetAt(0);
//
//                Map<String, Integer> colIndex = extracted(hsSheet);
//
////80001832288
//                for(int i=1; i<hsSheet.getPhysicalNumberOfRows(); i++) {
//                    JSONObject zipCode = CjAddress.getAddress(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("gngNumber"))),
//                            String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("consigneeAddress"))));
//
//                    Map testMAP = new HashMap<>();
//                    for(int idx = 1; idx < 16; idx++) {
//                        testMAP.put("printLabel" + idx, String.valueOf(zipCode.get("PRINT_LABEL" + idx)));
//                    }
//
//                    CJDto cjDto = CJDto.builder()
//                            .consigneeName(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("consigneeName"))))
//                            .consigneeTel(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("consigneeTel"))))
//                            .consigneeZipCode(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("consigneeZip"))))
//                            .consigneeAddress(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("consigneeAddress"))))
//                            .gngNumber(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("gngNumber"))))
//                            .cjNumber(String.valueOf(hsSheet.getRow(i).getCell(colIndex.get("cjNumber"))).replace("-",""))
//                            .hwbNo("111111")
//                            .printLabel1(testMAP.get("printLabel1").toString())
//                            .printLabel2(String.valueOf(testMAP.get("printLabel2")))
//                            .printLabel3(String.valueOf(testMAP.get("printLabel3")))
//                            .printLabel4(String.valueOf(testMAP.get("printLabel4")))
//                            .printLabel5(String.valueOf(testMAP.get("printLabel5")))
//                            .printLabel6(String.valueOf(testMAP.get("printLabel6")))
//                            .printLabel7(String.valueOf(testMAP.get("printLabel7")))
//                            .build();
//                    cjDtoList.add(cjDto);
//                }
//            } else {
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return cjDtoList;
//    }
//
//    private Map<String, Integer> extracted(HSSFSheet hsSheet) {
//        Row row = hsSheet.getRow(0);
//        Map map = new HashMap();
//        for (Cell cell : row) {
//            switch (cell.getStringCellValue()) {
//                case "받는분": map.put("consigneeName", cell.getColumnIndex()); break;
//                case "받는분 전화번호": map.put("consigneeTel", cell.getColumnIndex()); break;
//                case "받는분  우편번호": map.put("consigneeZip", cell.getColumnIndex()); break;
//                case "받는분주소": map.put("consigneeAddress", cell.getColumnIndex()); break;
//                case "상품명": map.put("gngNumber", cell.getColumnIndex()); break;
//                case "운송장번호": map.put("cjNumber", cell.getColumnIndex()); break;
//            }
//        }
//        return map;
//    }
//
////    @GetMapping("/user")
////    public OAuth2User user(String accessToken) {
////        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
////        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, accessToken, Instant.now(), Instant.MAX);
////
////        OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(clientRegistration, oAuth2AccessToken);
////
////        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
////        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(oAuth2UserRequest);
////
////        return oAuth2User;
////    }
////
////    @GetMapping("/oidc")
////    public OAuth2User oidc(String accessToken, String idToken) {
////        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
////        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, accessToken, Instant.now(), Instant.MAX);
////
////        OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(clientRegistration, oAuth2AccessToken);
////
////        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
////        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(oAuth2UserRequest);
////
////        return oAuth2User;
////    }
//}
