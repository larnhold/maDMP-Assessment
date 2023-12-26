package org.arnhold.evaluation.openaire;

import org.apache.jena.rdf.model.Model;

public interface OpenAireService {
    String test(Model madmp);

    void getPublications(Model madmp);
    String getResearchData(Model madmp);
    void getResearchSoftware(Model madmp);
    void getResearchProducts(Model madmp);
}
