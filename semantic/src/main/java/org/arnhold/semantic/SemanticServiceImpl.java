package org.arnhold.semantic;

import org.springframework.stereotype.Component;

@Component
public class SemanticServiceImpl implements SemanticService {

    @Override
    public void test() {
        System.out.println("test");
    }
}
