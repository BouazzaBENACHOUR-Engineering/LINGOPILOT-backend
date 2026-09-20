USE AiLanguageApp;
GO

IF EXISTS (SELECT 1 FROM dbo.subscription_plans WHERE UPPER(name) = 'FREE')
BEGIN
    UPDATE dbo.subscription_plans
    SET price = 0.00,
        currency = 'USD',
        billing_period = 'FREE',
        conversation_limit = 3,
        ads_enabled = 1,
        ai_messages_limit = NULL,
        is_active = 1
    WHERE UPPER(name) = 'FREE';
END
ELSE
BEGIN
    INSERT INTO dbo.subscription_plans(
        name,
        price,
        currency,
        billing_period,
        ai_messages_limit,
        speaking_limit,
        is_active,
        conversation_limit,
        ads_enabled
    )
    VALUES('FREE', 0.00, 'USD', 'FREE', NULL, NULL, 1, 3, 1);
END;

IF EXISTS (SELECT 1 FROM dbo.subscription_plans WHERE UPPER(name) = 'MONTHLY')
BEGIN
    UPDATE dbo.subscription_plans
    SET price = 4.99,
        currency = 'USD',
        billing_period = 'MONTHLY',
        conversation_limit = NULL,
        ads_enabled = 0,
        ai_messages_limit = NULL,
        is_active = 1
    WHERE UPPER(name) = 'MONTHLY';
END
ELSE
BEGIN
    INSERT INTO dbo.subscription_plans(
        name,
        price,
        currency,
        billing_period,
        ai_messages_limit,
        speaking_limit,
        is_active,
        conversation_limit,
        ads_enabled
    )
    VALUES('MONTHLY', 4.99, 'USD', 'MONTHLY', NULL, NULL, 1, NULL, 0);
END;

IF EXISTS (SELECT 1 FROM dbo.subscription_plans WHERE UPPER(name) = 'YEARLY')
BEGIN
    UPDATE dbo.subscription_plans
    SET price = 39.99,
        currency = 'USD',
        billing_period = 'YEARLY',
        conversation_limit = NULL,
        ads_enabled = 0,
        ai_messages_limit = NULL,
        is_active = 1
    WHERE UPPER(name) = 'YEARLY';
END
ELSE
BEGIN
    INSERT INTO dbo.subscription_plans(
        name,
        price,
        currency,
        billing_period,
        ai_messages_limit,
        speaking_limit,
        is_active,
        conversation_limit,
        ads_enabled
    )
    VALUES('YEARLY', 39.99, 'USD', 'YEARLY', NULL, NULL, 1, NULL, 0);
END;
GO

SELECT
    id,
    name,
    price,
    currency,
    billing_period,
    conversation_limit,
    ads_enabled,
    is_active
FROM dbo.subscription_plans
WHERE UPPER(name) IN ('FREE', 'MONTHLY', 'YEARLY')
ORDER BY price;
GO
