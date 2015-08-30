Rakennekuvaus

Sovelluslogiikkapakkaus sisältää luokat Peli, Pelilauta ja LaivojenLukija. Peli tarjoaa käyttöliittymälle palvelut pelin pelaamiseen. Pelilauta määrittelee 10x10-ruudun kokoisen pelilaudan. LaivojenLukijalla voi tarkastaa, ovatko laivat pelilaudalla oikein.

Pelaajaa vastaan pelaava tekoäly on toteutettu tekoalypakkaukseen. Luokka Tekoaly tarjoaa pelille palvelut tekoälyn käyttöön. Tekoäly käyttää toiminnassaan luokkia Ampuja, jolla se etsii ampumakohdan pelaajan laudalla, ja LaivojenAsettaja, jolla se asettaa omat laivansa laudalleen.

Kayttoliittymapakkaus sisältää graafisen käyttöliittymän toteutuksen. Pakkauksen luokat näyttävät pelin ruudulla ja sisältävät tapahtumankuuntelijat.

Pakkaus domain sisältää eräitä määrittelyjä ja vakioita. Lisäksi omassa pakkauksessaan on luokka Main, jonka tehtävänä on luoda peli ja käynnistää se.