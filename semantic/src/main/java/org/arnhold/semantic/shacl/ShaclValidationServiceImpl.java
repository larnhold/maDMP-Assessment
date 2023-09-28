package org.arnhold.semantic.shacl;

import org.springframework.stereotype.Service;

@Service
public class ShaclValidationServiceImpl implements ShaclValidationService {

    @Override
    public void test() {
        System.out.println("test");
    }
}
