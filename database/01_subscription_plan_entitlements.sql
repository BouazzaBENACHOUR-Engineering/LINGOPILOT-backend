USE AiLanguageApp;
GO

IF COL_LENGTH('dbo.subscription_plans', 'conversation_limit') IS NULL
BEGIN
    ALTER TABLE dbo.subscription_plans
    ADD conversation_limit INT NULL;
END;
GO

IF COL_LENGTH('dbo.subscription_plans', 'ads_enabled') IS NULL
BEGIN
    ALTER TABLE dbo.subscription_plans
    ADD ads_enabled BIT NOT NULL
        CONSTRAINT DF_subscription_plans_ads_enabled DEFAULT 0;
END;
GO
