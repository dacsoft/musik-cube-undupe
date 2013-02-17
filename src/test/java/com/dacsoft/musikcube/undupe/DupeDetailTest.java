package com.dacsoft.musikcube.undupe;


import com.dacsoft.musikcube.undupe.fixtures.RandomDupeDetail;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Unit test for simple App.
 */
public class DupeDetailTest extends Assert
{

    @Test
    public void GivenEqualDetails_ThenReturnsFirst() throws Exception {

        DupeDetail det1 = new RandomDupeDetail();
        DupeDetail det2 = (DupeDetail) det1.clone();

        assertEquals( "should be equal", 0, new DupeComparator().compare(det1, det2));
    }
    @Test
    public void GivenEqualDetails_WhenArtistDifferent_ThenReturnsFirstArtist() throws Exception {

        DupeDetail det1 = new RandomDupeDetail();
        det1.artist = "The Artist Formerly Known as Prince";
        DupeDetail det2 = (DupeDetail) det1.clone();
        det2.artist = "Prince";

        assertThat( "artist sorts by alpha", new DupeComparator().compare(det1, det2), is(greaterThan(0)));
        assertThat( "artist sorts by alpha", new DupeComparator().compare(det2, det1), is(lessThan(0)));
    }

    @Test
    public void GivenEqualDetails_WhenTitleDifferent_ThenReturnsFirstTitle() throws Exception {

        DupeDetail det1 = new RandomDupeDetail();
        det1.title = "Spanish Moon";
        DupeDetail det2 = (DupeDetail) det1.clone();
        det2.title = "Bohemian Rhapsody";

        assertThat( "title sorts by alpha", new DupeComparator().compare(det1, det2), is(greaterThan(0)));
        assertThat( "title sorts by alpha 2", new DupeComparator().compare(det2, det1), is(lessThan(0)));
    }
    @Test
    public void GivenEqualDetails_WhenAlbumDifferent_ThenReturnsFirstAlbum() throws Exception {

        DupeDetail det1 = new RandomDupeDetail();
        det1.album = "Dark Side of the Moon";
        DupeDetail det2 = (DupeDetail) det1.clone();
        det2.album = "Bohemian Rhapsody";

        assertThat( "album sorts by alpha", new DupeComparator().compare(det1, det2), is(greaterThan(0)));
        assertThat( "album sorts by alpha 2", new DupeComparator().compare(det2, det1), is(lessThan(0)));
    }
}
