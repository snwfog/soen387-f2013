package com.charlescy.model;

public class Thumbnail
{
  String thumbnail;
  public Thumbnail()
  {
    thumbnail = "assets/img/cover_not_found.png";
  }

  public String getThumbnail()
  {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail)
  {
    this.thumbnail = thumbnail;
  }
}
