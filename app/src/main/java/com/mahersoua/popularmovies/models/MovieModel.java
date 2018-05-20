package com.mahersoua.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieModel implements Parcelable {
    private String homePage;
    private String backdropPath;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private String status;
    private String tagline;
    private String title;
    private int[] genreIds;
    private List<GenreModel> genres;
    private List<MovieCollectionModel> belongsToCollection;
    private List<ProductionCompaniesModel> productionCompanies;
    private List<ProductionCountryModel> productionCountries;
    private List<SpokenLanguageModel> spokenLanguages;
    private boolean isAdult;
    private boolean isVideo;
    private float budget;
    private float revenue;
    private Double popularity;
    private float voteAverage;
    private int id;
    private int runtime;
    private int voteCount;

    public MovieModel(){

    }

    private MovieModel(Parcel in) {
        homePage = in.readString();
        backdropPath = in.readString();
        imdbId = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        genreIds = in.createIntArray();
        isAdult = in.readByte() != 0;
        isVideo = in.readByte() != 0;
        budget = in.readFloat();
        revenue = in.readFloat();
        popularity = in.readDouble();
        voteAverage = in.readFloat();
        id = in.readInt();
        runtime = in.readInt();
        voteCount = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    public List<MovieCollectionModel> getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(List<MovieCollectionModel> belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public List<ProductionCompaniesModel> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompaniesModel> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountryModel> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountryModel> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<SpokenLanguageModel> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguageModel> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(homePage);
        dest.writeString(backdropPath);
        dest.writeString(imdbId);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(title);
        dest.writeIntArray(genreIds);
        dest.writeByte((byte) (isAdult ? 1 : 0));
        dest.writeByte((byte) (isVideo ? 1 : 0));
        dest.writeFloat(budget);
        dest.writeFloat(revenue);
        dest.writeDouble(popularity);
        dest.writeFloat(voteAverage);
        dest.writeInt(id);
        dest.writeInt(runtime);
        dest.writeInt(voteCount);
    }
}
