package org.arnhold.evaluation.openaire.service;

import org.apache.jena.rdf.model.Model;

public interface OpenAireService {
    void test(Model madmp);

    void getPublications(Model madmp);
    void getResearchData(Model madmp);
    void getResearchSoftware(Model madmp);
    void getResearchProducts(Model madmp);
}
