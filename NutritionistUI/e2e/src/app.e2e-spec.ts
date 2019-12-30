import { AppPage } from './app.po';
import { browser, by } from 'protractor';
import { protractor } from 'protractor/built/ptor';
import { element } from '@angular/core/src/render3/instructions';
import { async } from 'rxjs/internal/scheduler/async';

describe('Nutritionist Front end App', () => {
  let page: AppPage;
  var username = '';

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Nutritionist');
  });

  it('should redirect to login page', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should redirect to register page', () => {
    username = new Date().getUTCMilliseconds().toString();
    browser.element(by.buttonText('Register')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.name('firstname')).sendKeys('Super');
    browser.element(by.name('lastname')).sendKeys('User');
    browser.element(by.name('user_id')).sendKeys(username);
    browser.element(by.name('password')).sendKeys('password');
    browser.element(by.buttonText('Register')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user and navigate to search page', () => {
    browser.element(by.name('userId')).sendKeys(username);
    browser.element(by.name('password')).sendKeys('password');
    browser.element(by.buttonText('Login')).click();
    expect(browser.getCurrentUrl()).toContain('/search');
  });

  it('should be able to search for food', () => {
    browser.element(by.buttonText('Search')).click();
    expect(browser.getCurrentUrl()).toContain('food/search');
    browser.element(by.id('search-button-input')).sendKeys('carrot');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = browser.element.all(by.css('.food-title'));
    expect(searchItems.count()).toBeGreaterThan(10);
    for (let i =0; i<2; i++) {
      expect(searchItems.get(i).getText()).toContain('Super');
    }
  });

  it('should be able to add food to favourite',async () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = browser.element.all(by.css('mat-card-title'));
    expect(searchItems.count()).toBeGreaterThan(10);
    browser.element(by.buttonText('Add to favourite')).click();
  });

it('should be able to delete food from favourite',async () => {
    browser.element(by.buttonText('favourite')).click();
    expect(browser.getCurrentUrl()).toContain('/favourite');
    const searchItems = browser.element.all(by.css('.food-title'));
    expect(searchItems.count()).toBe(1);
    browser.element(by.buttonText('Delete')).click();
    expect(searchItems.count()).toBe(0);
  });

  it('should be able to logout',async () => {
    browser.element(by.buttonText('Logout')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });
});
