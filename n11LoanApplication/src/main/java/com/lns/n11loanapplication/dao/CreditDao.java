package com.lns.n11loanapplication.dao;

import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditDao {
    @Autowired
    CreditRepository creditRepository;

    public List<Credit> findAll()
    {
        return creditRepository.findAll();
    }

    public Credit findById(Long id)
    {
        Optional<Credit> credit =creditRepository.findById(id);
        return credit.orElse(null);
        //TODO: null yerine farklı bir dönüş sağlanabilir.
    }

    public Credit save (Credit credit)
    {
        return creditRepository.save(credit);
    }

    public void deleteById(Long id)
    {
        creditRepository.deleteById(id);
    }

    public void deleteAll()
    {
        creditRepository.deleteAll();
    }
}
