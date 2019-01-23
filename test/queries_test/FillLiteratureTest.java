package queries_test;

import static junit.framework.TestCase.assertEquals;

import attributes.APACitation;
import attributes.enzyme_structure.AASequence;
import attributes.enzyme_structure.MolecularWeight;
import attributes.functional_parameters.Km;
import attributes.organism_related_information.Organism;
import client.DefaultUser;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import queries.FastaQuery;
import queries.FillLiterature;

public class FillLiteratureTest {

  private FillLiterature fillLiterature;
  private FillLiterature pubmedFillerLiterature;
  private Protein protein1;
  private Protein protein2;
  private Km km;
  private MolecularWeight molecularWeight;
  private AASequence aaSequence;
  private Literature literature;
  private FastaQuery query;

  @Before
  public void SetUp() throws Exception {
    fillLiterature = new FillLiterature(new DefaultUser());
    pubmedFillerLiterature = new FillLiterature(new DefaultUser(), false);
    protein1 = new Protein(
        new Enzyme(1,1,1,1,new DefaultUser()),
        new Organism(
            "Pseudomonas putida",
            "probable alcohol dehydrogenase",
            new Literature(678779)
        ),
        "Q76HN6"
    );
    molecularWeight = new MolecularWeight(
        56700,
        "deduced from amino acid sequence ",
        new Literature(678779)
    );
    query = new FastaQuery(new DefaultUser());
    query.setEntities(protein1);
    aaSequence = (AASequence) query.getResult().get(0);
    protein1.addAttributes(molecularWeight, aaSequence);

    protein2 = new Protein(
        new Enzyme(1,1,1,1, new DefaultUser()),
        new Organism(
            "Thermus thermophilus",
            "",
            new Literature(684583),
            new Literature(737200)
        ),
        ""
    );
    km = new Km(
        27.6,
        "1-Indanone",
        "65°C; pH 10.5, 65°C",
        new Literature(684583)
    );
    molecularWeight = new MolecularWeight(
        26961,
        "4 * 26961, calculated from sequence",
        new Literature(684583)
    );
    aaSequence = new AASequence("");
    protein2.addAttributes(km, molecularWeight, aaSequence);
  }

  @Test
  public void oneProteinTest() throws Exception {
    fillLiterature.setProteins(protein1);
    pubmedFillerLiterature.setProteins(protein1);
    List<Protein> proteins = fillLiterature.fill();
    assertEquals(1, proteins.size());
    literature = new Literature(
        16926497,
        678779,
        new APACitation(
            "Isolation and characterization of an alcohol dehydrogenase gene from the octylphenol polyethoxylate degrader Pseudomonas putida S-5",
            "Biosci. Biotechnol. Biochem.",
            70,
            1855,
            1863,
            2006,
            "Tasaki, Y.", "Yoshikawa, H.","Tamura, H."
        )
    );
    assertEquals(literature, proteins.get(0).getOrganism().getReferences().get(0));
    assertEquals(literature, proteins.get(0).getAttribute().get(0).getReferences().get(0));
    assertEquals(0, proteins.get(0).getAttribute().get(1).getReferences().size());
    proteins = pubmedFillerLiterature.fill();
    literature = new Literature(678779, 16926497);
    assertEquals(literature, proteins.get(0).getOrganism().getReferences().get(0));
    assertEquals(literature, proteins.get(0).getAttribute().get(0).getReferences().get(0));
    assertEquals(0, proteins.get(0).getAttribute().get(1).getReferences().size());
  }

  @Test
  public void twoProteinTest() throws Exception {
    fillLiterature.setProteins(protein1, protein2);
    pubmedFillerLiterature.setProteins(protein1, protein2);
    List<Protein> proteins = fillLiterature.fill();
    assertEquals(2, proteins.size());

    literature = new Literature(
        19807673,
        737200,
        new APACitation(
            "Insight into the stereospecificity of short-chain thermus thermophilus alcohol dehydrogenase showing pro-S hydride transfer and prelog enantioselectivity",
            "Protein Pept. Lett.",
            17,
            437,
            443,
            2010,
            "Pennacchio, A.", "Giordano, A.", "Esposito, L.", "Langella, E.", "Rossi, M.", "Raia, C.A."
        )
    );
    assertEquals(literature, proteins.get(1).getOrganism().getReferences().get(1));
    literature = new Literature(
        18456852,
        684583,
        new APACitation(
            "Purification and characterization of a novel recombinant highly enantioselective short-chain NAD(H)-dependent alcohol dehydrogenase from Thermus thermophilus",
            "Appl. Environ. Microbiol.",
            74,
            3949,
            3958,
            2008,
            "Pennacchio, A.", "Pucci, B.", "Secundo, F.", "La Cara, F.", "Rossi, M.", "Raia, C.A."
        )
    );
    assertEquals(literature, proteins.get(1).getOrganism().getReferences().get(0));
    assertEquals(literature, proteins.get(1).getAttribute().get(0).getReferences().get(0));
    assertEquals(literature, proteins.get(1).getAttribute().get(1).getReferences().get(0));
    assertEquals(0, proteins.get(1).getAttribute().get(2).getReferences().size());

    proteins = pubmedFillerLiterature.fill();
    assertEquals(2, proteins.size());
    literature = new Literature(684583, 18456852);
    assertEquals(literature, proteins.get(1).getOrganism().getReferences().get(0));
    assertEquals(literature, proteins.get(1).getAttribute().get(0).getReferences().get(0));
    assertEquals(literature, proteins.get(1).getAttribute().get(1).getReferences().get(0));
    assertEquals(0, proteins.get(1).getAttribute().get(2).getReferences().size());
    literature = new Literature(737200, 19807673);
    assertEquals(literature, proteins.get(1).getOrganism().getReferences().get(1));
  }


}