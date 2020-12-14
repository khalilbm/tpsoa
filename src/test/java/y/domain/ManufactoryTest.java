package y.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import y.web.rest.TestUtil;

public class ManufactoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufactory.class);
        Manufactory manufactory1 = new Manufactory();
        manufactory1.setId(1L);
        Manufactory manufactory2 = new Manufactory();
        manufactory2.setId(manufactory1.getId());
        assertThat(manufactory1).isEqualTo(manufactory2);
        manufactory2.setId(2L);
        assertThat(manufactory1).isNotEqualTo(manufactory2);
        manufactory1.setId(null);
        assertThat(manufactory1).isNotEqualTo(manufactory2);
    }
}
