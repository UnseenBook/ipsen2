package leen.meij;

import leen.meij.utilities.Site;

public final class Rechten
{
	// bits
	public static final int None = 0;
	public static final int ReserveringenAanpassen = 1 << 0;
	public static final int KlantenAanpassen = 1 << 1;
	public static final int GebruikersAanpassen = 1 << 2;
	public static final int VoertuigenAanpassen = 1 << 3;
	public static final int ReserveringenRaadplegen = 1 << 4;
	public static final int KlantenRaadplegen = 1 << 5;
	public static final int GebruikersRaadplegen = 1 << 6;
	public static final int VoertuigenRaadplegen = 1 << 7;

	public static final int Inleverlijst = 1 << 8;
	public static final int Huurlijst = 1 << 9;
	public static final int Management = 1 << 10;

	// combinations
	public static final int Reserveringen = ReserveringenAanpassen | ReserveringenRaadplegen;
	public static final int Klanten = KlantenAanpassen | KlantenRaadplegen;
	public static final int Gebruikers = GebruikersAanpassen | GebruikersRaadplegen;
	public static final int Voertuigen = VoertuigenAanpassen | VoertuigenRaadplegen;

	public static final int All = (1 << 31) - 1; // set all bits to 1

	/**
	 * Returns a value indicating wether the current Gebruiker has a specified recht.
	 * @param recht The recht to check.
	 * @return A value indicating wether the current Gebruiker has the specified recht.
	 */
	public static boolean heeftRecht(int recht)
	{
		int rechten = 0;
		try
		{
			rechten = Site.getInstance().getGebruiker().getAfdeling().getRechten();
		}
		catch (NullPointerException e)
		{
		}

		return ((rechten & recht) == recht) || true; // TODO: remove '|| true'
	}

}