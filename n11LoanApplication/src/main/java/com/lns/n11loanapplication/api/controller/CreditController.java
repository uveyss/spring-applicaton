package com.lns.n11loanapplication.api.controller;

import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/credit/")
public class CreditController {
    @Autowired
    CreditService creditService;



    @GetMapping("{tckn}/{birthDate}/")
    public ApiResponse findUserCredit(@Valid @PathVariable Long tckn, @Valid @PathVariable String birthDate)
    {
        UserCreditDto userCreditDto= creditService.prepareUserCreditDtoForCreditApproval(tckn,birthDate);
        return ApiResponse.success(userCreditDto);
    }




}
