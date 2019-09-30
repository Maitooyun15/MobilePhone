package com.example.mobilephone.model

class DataModel {
    companion object {
        fun getMobileList(): List<MobileModel> {
            return listOf(
                MobileModel(
                    1,
                    "Moto E4 Plus",
                    "First place in our list goes to the excellent Moto E4 Plus. It's a cheap phone that features phenomenal battery life, a fingerprint scanner and a premium feel design, plus it's a lot cheaper than the Moto G5 below. It is a little limited with its power, but it makes up for it by being able to last for a whole two days from a single charge. If price and battery are the most important features for you, the Moto E4 Plus will suit you perfectly.",
                    "Samsung",
                    "https://cdn.mos.cms.futurecdn.net/grwJkAGWQp4EPpWA3ys3YC-650-80.jpg",
                    4.9,
                    179.99,
                    false
                ),
                MobileModel(
                    2,
                    "Nokia 6",
                    "Nokia is back in the mobile phone game and after a small price drop to the Nokia 6 we've now seen it enter our best cheap phone list. It comes with a Full HD 5.5-inch display, full metal design and a fingerprint scanner for added security. The battery isn't incredible on the Nokia 6, but it's not awful either making this one of our favorite affordable phones on the market right now.",
                    "Nokia",
                    "https://cdn.mos.cms.futurecdn.net/8LWUERoxMAWavvVAAbxuac-650-80.jpg",
                    4.6,
                    199.99,
                    false
                ),
                MobileModel(
                    3,
                    "Moto G4 Plus",
                    "The spec for the G4 Plus is much the same as it was on the Moto G4, but it also comes with a fingerprint scanner and an improved camera. The 16MP rear shooter is arguably one of the most impressive phone cameras at the sub-£200 mark. There's no NFC so you won't be able to use Android Pay on the Moto G4 Plus, but a bright display and strong performance is sure to make up for it.",
                    "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/52JJgbgWiGftNHV5UmMDfS-650-80.jpg",
                    4.7,
                    179.0,
                    false
                ),
                MobileModel(
                    4,
                    "Moto G5",
                    "Motorola's Moto G5, a former best cheap phone in the world, has slipped a few places thanks to better priced alternatives, plus the introduction of the new G5S. The Moto G5 comes with a metal design, 1080p display and fingerprint scanner. You won't get the fastest chipset on this list or NFC with the Moto G5, but as an all-round product the cheap Motorola phone will serve you well.",
                    "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/DcUtY6YfhoajHnoKUgGFqn-650-80.jpg",
                    3.8,
                    165.0,
                    false
                ),
                MobileModel(
                    5,
                    "Sony Xperia L1",
                    "Currently the only Sony handset to take a position in our best cheap phone list, the Xperia L1 is a low priced handset that does okay but won't blow your socks off. Other phones in this list are far more impressive as this doesn't have a fingerprint scanner or stunning camera. That said, the price is low and if you really, really like the design of Sony handsets everything here could add up to being your best choice for a cheap phone. We particularly liked the 5.5-inch display - despite its 720p resolution - and the expandable storage too, which allows you to use microSD cards up to 256GB. ",
                    "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/7dUFmtHkmH7La9dFzew7Ri-650-80.jpg",
                    4.3,
                    171.99,
                    false
                )
            )
        }

        fun getMobileImageId(): List<MobileImageModel> {
            return listOf(
                MobileImageModel(
                    3,
                    3,
                    "https://www.91-img.com/gallery_images_uploads/9/5/95483e778ba595de71ba90fe06675dcf8b9f9d0a.jpg"
                ),
                MobileImageModel(
                    10,
                    3,
                    "http://www.91-img.com/pictures/moto-moto-g4-plus-mobile-phone-large-2.jpg"
                ),
                MobileImageModel(
                    11,
                    3
                    , "http://www.91-img.com/pictures/moto-moto-g4-plus-mobile-phone-large-3.jpg"
                )
            )
        }

        fun getFavoriteList(): ArrayList<MobileModel> {
            return arrayListOf(
                MobileModel(
                    1,
                    "Moto E4 Plus",
                    "First place in our list goes to the excellent Moto E4 Plus. It's a cheap phone that features phenomenal battery life, a fingerprint scanner and a premium feel design, plus it's a lot cheaper than the Moto G5 below. It is a little limited with its power, but it makes up for it by being able to last for a whole two days from a single charge. If price and battery are the most important features for you, the Moto E4 Plus will suit you perfectly.",
                    "Samsung",
                    "https://cdn.mos.cms.futurecdn.net/grwJkAGWQp4EPpWA3ys3YC-650-80.jpg",
                    4.9,
                    179.99,
                    true
                ),
                MobileModel(
                    2,
                    "Nokia 6",
                    "Nokia is back in the mobile phone game and after a small price drop to the Nokia 6 we've now seen it enter our best cheap phone list. It comes with a Full HD 5.5-inch display, full metal design and a fingerprint scanner for added security. The battery isn't incredible on the Nokia 6, but it's not awful either making this one of our favorite affordable phones on the market right now.",
                    "Nokia",
                    "https://cdn.mos.cms.futurecdn.net/8LWUERoxMAWavvVAAbxuac-650-80.jpg",
                    4.6,
                    199.99,
                    true
                ),
                MobileModel(
                    3,
                    "Moto G4 Plus",
                    "The spec for the G4 Plus is much the same as it was on the Moto G4, but it also comes with a fingerprint scanner and an improved camera. The 16MP rear shooter is arguably one of the most impressive phone cameras at the sub-£200 mark. There's no NFC so you won't be able to use Android Pay on the Moto G4 Plus, but a bright display and strong performance is sure to make up for it.",
                    "Motorola",
                    "https://cdn.mos.cms.futurecdn.net/52JJgbgWiGftNHV5UmMDfS-650-80.jpg",
                    4.7,
                    179.0,
                    true
                )
            )
        }
    }
}